import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * FilteringMethods class that will handle data loading, array handling for all the Filtering Methods
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */
public class FilteringMethods {

	private float[] inputData, outputData;
	private float[] startEdges, endEdges;
	private float[][] filteringData;
	private SequentialFiltering sequential;
	private ParallelFiltering parallel;
	private fileHandler fileHandler;
	private ForkJoinPool forkJoinPool;  

	/**
	 * Constructor to initialise instance with type of Filtering Method as the parameter
	 */
	public FilteringMethods() {
		this.sequential = new SequentialFiltering();
		this.parallel = new ParallelFiltering();
		this.forkJoinPool = new ForkJoinPool();
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
	 * Method to use inputData, add the boundries and run the Filtering Method and produce the filteringData results
	 * 
	 * @param filter_size filtering size
	 */
	public void process(int filter_size){
		arrayHandler arrayHandler = new arrayHandler(this.inputData, filter_size);
		startEdges = new float[filter_size/2];
		endEdges = new float[filter_size/2];
		for (int i = 0, j = filter_size/2; i < filter_size/2; i++, j--) {
			startEdges[i] = this.inputData[i];
			endEdges[i] = this.inputData[this.inputData.length - j];
		}
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
				sequential.setDataArray(getFilteringData());
				setOutputData(sequential.compute());
				break;
			case PARALLEL:
				parallel.setDataArray(getFilteringData());
				setOutputData(forkJoinPool.invoke(parallel));
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
		fileHandler.saveOutputToFile(outputData, startEdges, endEdges, type, file);		
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
	private float[][] getFilteringData() {
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

	public void setThreshold(int threshold) {
		parallel.setThreshold(threshold);
	}

	/**
	 * Get the start Edges
	 * 
	 * @return startEgdes
	 */
	public float[] getStartEdges() {
		return this.startEdges;
	}

	/**
	 * Get the end Edges
	 * 
	 * @return endEgdes
	 */
	public float[] getEndEdges() {
		return this.endEdges;
	}
} 