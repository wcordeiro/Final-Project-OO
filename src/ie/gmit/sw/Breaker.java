package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Breaker {
	private String encryptedText;
	private ArrayBlockingQueue<Result> results;
	private Integer capacity;
	private final Result POISON_PILL;
	private Result finalResult;

	public Breaker(String encryptedText) {
		super();
		this.encryptedText = encryptedText;
		this.capacity = this.encryptedText.length()/2;
		this.results = new ArrayBlockingQueue<Result>(capacity);
		POISON_PILL = new Result(-1,"Poison",-1.0);
		ExecutorService executorProducer = Executors.newFixedThreadPool(capacity);
		ExecutorService executorConsumer = Executors.newFixedThreadPool(1);
		
		Consumer consumer = new Consumer();
		
		executorConsumer.execute(consumer);
		
		for(int i = 2; i < this.capacity ; i++){
			executorProducer.execute(new Decrypter(i));
		}
		executorProducer.shutdown();
		try {
			results.put(POISON_PILL);
		} catch (InterruptedException e) {
			System.out.println("Poison Pill Error");
			e.printStackTrace();
		}
		
		executorConsumer.shutdown();
		this.finalResult = consumer.getMaxResult();
	}
	
	class Decrypter implements Runnable{
		private Result result;
		private TextScorer scorer;
		private RailFence decrypter;
		private Integer key;
		
		public Decrypter(Integer key) {
			super();
			this.key = key;
			this.decrypter = new RailFence();
			QuadGramMap fourGram = new QuadGramMap();
			this.scorer = new TextScorer(fourGram.getFourGrams());
		}

		@Override
		public void run() {
			String decryptedText = decrypter.decrypt(encryptedText, key);
			Double score = scorer.getScore(decryptedText);
			result = new Result(key,decryptedText,score);
			try {
				results.put(result);
			} catch (InterruptedException e) {
				System.out.println("Error with the Blocking Queue");
				e.printStackTrace();
			}
		}

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		public TextScorer getScorer() {
			return scorer;
		}

		public void setScorer(TextScorer scorer) {
			this.scorer = scorer;
		}

		public RailFence getDecrypter() {
			return decrypter;
		}

		public void setDecrypter(RailFence decrypter) {
			this.decrypter = decrypter;
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}
		
	}
	
	class Consumer implements Runnable{
		private Double MaxScore;
		private Result result;
		private Result maxResult;
		private Boolean isRunning;
		
		Consumer(){
			super();
			this.MaxScore = new Double(0);
			this.isRunning = true;
		}
		
		@Override
		public void run() {
			while(isRunning){
				try {
					result = results.take();
				} catch (InterruptedException e) {
					System.out.println("Error on the Consumer");
					e.printStackTrace();
				}
				if(result.equals(POISON_PILL)){
					this.stopRunning();
				}
				else if(result.getScore() > MaxScore){
					MaxScore = result.getScore();
					maxResult = result;
				}
			}
		}
		
		private void stopRunning(){
			isRunning = false;
		}

		public Result getMaxResult() {
			return maxResult;
		}

		public void setMaxResult(Result maxResult) {
			this.maxResult = maxResult;
		}
		
		
	}
	
	public String getEncryptedText() {
		return encryptedText;
	}

	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}

	public ArrayBlockingQueue<Result> getResults() {
		return results;
	}

	public void setResults(ArrayBlockingQueue<Result> results) {
		this.results = results;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Result getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(Result finalResult) {
		this.finalResult = finalResult;
	}
	
}
