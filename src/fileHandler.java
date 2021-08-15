import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * File handling class that will:
 * 	- open input data file
 * 	- get file contents
 * 	- create an array 
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0 
 */
public class fileHandler {
	private float[] inputData;
	private Scanner fileReader;
	private FileOutputStream outputFile;

	/**	
	 * Constructor to create fileHandler instance
	 * 
	 * @param filename file that needs to be opened
	 * @throws FileNotFoundException when file cannot be found
	 */
	public fileHandler(String filename) throws FileNotFoundException {
		this.fileReader = new Scanner(new File(filename));
		this.inputData = new float[Integer.parseInt(this.fileReader.nextLine())];
		this.outputFile = null;
	}

	/**
	 * Method to populate input data file contents into array
	 */
	private void populate(){
		int i = 0;
		while (this.fileReader.hasNextLine()){
			String num = this.fileReader.nextLine().split(" ")[1].replace(",", ".");
			this.inputData[i] = Float.parseFloat(num);
		}
		this.fileReader.close();
	}

	/**
	 * Method to return Data Array
	 * 
	 * @return inputData
	 */
	public float[] getInputData() {
		populate();
		return this.inputData;
	}

	/**
	 * Method to save output to file
	 * 
	 * @param outputData array
	 * @throws FileNotFoundException if file is not found
	 * @throws IOException for IO Exceptions
	 */
	public void saveOutputToFile(float[] outputData) throws FileNotFoundException, IOException{
		try {
			outputFile = new FileOutputStream("sampleOutput/sampleOutput" + Integer.toString(outputData.length) + ".txt");
		} finally {
			if (outputFile != null) {
				outputFile.close();
			}
		}	
	}
}