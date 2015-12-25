package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile implements FileInterface {
	
	private String fileName;
	private FileReader fileReader;
	private BufferedReader in;
	private String inputText;
	
	public InputFile(String fileName) {
		super();
		this.fileName = fileName;
	    inputText = new String();
	}

	@Override
	public void open() throws FileNotFoundException {
		this.fileReader = new FileReader(fileName);
		this.in = new BufferedReader (this.fileReader);
	}

	@Override
	public void close() throws IOException {
			in.close();
	}

	@Override
	public void read() throws IOException {
		String line;
		line = in.readLine();
		this.parse(line);
	}

	@Override
	public void parse(String line) {
		this.inputText = line;
	}

	@Override
	public void write() throws IOException {

	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	
}
