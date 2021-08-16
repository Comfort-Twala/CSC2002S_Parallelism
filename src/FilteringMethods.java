import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FilteringMethods class that will handle data loading, array handling for all the Filtering Methods
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */
public class FilteringMethods {

	private float[] inputData;
	private float[] outputData;
	private float[][] filteringData;
	private SequentialFiltering sequential;
	private ParallelFiltering parallel;
	private fileHandler fileHandler;  

	/**
	 * Constructor to initialise instance with type of Filtering Method as the parameter
	 */
	public FilteringMethods() {
		this.sequential = new SequentialFiltering();
		this.parallel = new ParallelFiltering();
	}

	/**
	 * enum for type of Filtering Method instance is
	 */
	enum Type {
		SEQUENTIAL,
		PARALLEL
	}

	/**
	 * Method to open file and populate inputData with data from file
	 * 
	 * @param filename inputdatafile
	 * @throws FileNotFoundException if file is not found
	 */
	public void loadData(String filename) throws FileNotFoundException{
		fileHandler = new fileHandler(filename);
		this.setInputData(fileHandler.getInputData());
	}

	/**
	 * Method to use inputData, run the Filtering Method and produce the filteringData results
	 * 
	 * @param filter_size filtering size
	 */
	public void process(int filter_size){
		arrayHandler arrayHandler = new arrayHandler(this.inputData, filter_size);
		setFilteringData(arrayHandler.neighbouringArrays());		
	} 	

	/**
	 * Method to run the Filtering Method and set the results as output data
	 * 
	 * @param type Filtering Method used
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void execute(Type type) throws FileNotFoundException, IOException{
		switch (type) {
			case SEQUENTIAL:
				setOutputData(sequential.compute());
				break;
			case PARALLEL:
				setOutputData(parallel.compute());
				break;
		}
	}

	/**
	 * Method to write outputData to file 
	 * 
	 * @param type
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveData(Type type, String file) throws FileNotFoundException, IOException {
		fileHandler.saveOutputToFile(outputData, type, file);		
	}
	
	/**
	 * Get the inputData
	 * 
	 * @return inputData
	 */
	public float[] getInputData() {
		return inputData;
	}

	/**
	 * Set the inputData
	 * 
	 * @param inputData
	 */
	private void setInputData(float[] inputData) {
		this.inputData = inputData;
	}
	
	/**
	 * Get the outputData
	 * 
	 * @return outputData
	 */
	public float[] getOutputData() {
		return outputData;
	}
		
	/**
	 * Set the outputData
	 * 
	 * @param outputData
	 */
	private void setOutputData(float[] outputData) {
		this.outputData = outputData;
	}
	
	/**
	 * Get the filteringData
	 * 
	 * @return filteringData
	 */
	public float[][] getFilteringData() {
		return filteringData;
	}

	/**
	 * Set the filteringData
	 * 
	 * @param filteringData
	 */
	private void setFilteringData(float[][] filteringData) {
		this.filteringData = filteringData;
	}	
} 