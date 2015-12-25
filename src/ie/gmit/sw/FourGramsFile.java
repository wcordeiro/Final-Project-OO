package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class FourGramsFile implements FileInterface {
	
	private final static String FILE_NAME = "4grams.txt";
	private FileReader fileReader;
	private BufferedReader in;
	private ConcurrentHashMap <String,Double> fileParts;
	
	public FourGramsFile() {
		super();
		try {
			this.fileReader = new FileReader(FILE_NAME);
		} catch (FileNotFoundException e) {
			System.out.println("File " +  FILE_NAME + " not found");
		}
		fileParts = new ConcurrentHashMap <String,Double>();
	}

	@Override
	public void open() throws FileNotFoundException {
		this.in = new BufferedReader (this.fileReader);

	}

	@Override
	public void close() throws IOException {
		in.close();

	}

	@Override
	public void read() throws IOException {
		String line;
		while((line = in.readLine()) != null) {
		    this.parse(line);
		}
	}

	@Override
	public void parse(String line) {
		String delims = " ";
		String[] tokens = line.split(delims);
		fileParts.put(tokens[0],Double.parseDouble(tokens[1]));

	}

	@Override
	public void write() throws IOException {
		// TODO Auto-generated method stub

	}

	public FileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public ConcurrentHashMap<String, Double> getFileParts() {
		return fileParts;
	}

	public void setFileParts(ConcurrentHashMap<String, Double> fileParts) {
		this.fileParts = fileParts;
	}

	public static String getFileName() {
		return FILE_NAME;
	}

}
