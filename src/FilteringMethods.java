import java.io.FileNotFoundException;

/**
 * Abstract FilteringMethods class that will handle data loading, array handling for all the Filtering Methods
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */
abstract public class FilteringMethods {

	private float[] inputData;
	private float[] outputData;
	private float[][] filteringData;

	/**
	 * Method to open file and populate inputData with data from file
	 * 
	 * @param filename inputdatafile
	 * @throws FileNotFoundException if file is not found
	 */
	public void loadData(String filename) throws FileNotFoundException{
		fileHandler fileHandler = new fileHandler(filename);
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
		setOutputData(execute());
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
	
	/**
	 * Method to run the Filtering Method and return the results as output data
	 * 
	 * @return outputData
	 */
	public abstract float[] execute();
} 