/**
 * Sequential Filtering class that will calculate and process the Medians of arrays in a logical order (one-by-one)
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */
public class SequentialFiltering {
	private float[][] dataArray;
	private float[] resultArray;

	/**
	 * Constructor to create SequentialFiltering instance
	 */
	public SequentialFiltering() {}

	/**
	 * Main algorithm that computes the median of the subarrays one by one
	 * 
	 * @return array of medians
	 */
	public float[] compute() {
		resultArray = new float[dataArray.length];
		for (int i = 0; i < dataArray.length; i++){
			resultArray[i] = arrayHandler.median(dataArray[i]);
		}
		return resultArray;
	}

	/**
	 * Set the dataArray
	 * 
	 * @param dataArray
	 */
	public void setDataArray(float[][] dataArray) {
		this.dataArray = dataArray;
	}
}