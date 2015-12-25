package ie.gmit.sw;

public class Result {
	private Integer key;
	private String plainText;
	private Double score;
	
	public Result(Integer key, String plainText, Double score) {
		super();
		this.key = key;
		this.plainText = plainText;
		this.score = score;
	}
	
	public void print(){
		System.out.println("PlainText: " + this.plainText);
		System.out.println("Score: " + this.score);
		System.out.println("Key: " + this.key);
	}
	
	public Integer getKey() {
		return key;
	}
	
	public void setKey(Integer key) {
		this.key = key;
	}
	
	public String getPlainText() {
		return plainText;
	}
	
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	
	public Double getScore() {
		return score;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
}
