package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class QuadGramMap {
	public static final int GRAM_SIZE = 4;
	private ConcurrentHashMap <String,Double> fourGrams;
	
	public QuadGramMap() {
		super();
		FourGramsFile file = new FourGramsFile();
		try {
			file.open();
			file.read();
			this.fourGrams = file.getFileParts();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("4grams.txt not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("4grams.txt can't be accessed");
			e.printStackTrace();
		}
	}

	public ConcurrentHashMap<String, Double> getFourGrams() {
		return fourGrams;
	}

	public void setFourGrams(ConcurrentHashMap<String, Double> fourGrams) {
		this.fourGrams = fourGrams;
	}
	
}
