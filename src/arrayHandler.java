import java.util.Arrays;

/**
 * arrayHandler class that handle all Array methods making it easier to manipulate
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */
public class arrayHandler {
	private float[] dataArray;
	private int filter_size;

	/**
	 * Constructor to create arrayHandler instance
	 * 
	 * @param array Data array that needs handling
	 * @param filter_size Size of odd number filter used
	 */
	public arrayHandler(float[] array, int filter_size) {
		this.dataArray = array;
		this.filter_size = filter_size;		
	}

	/**
	 * Method that takes main array and splits it into its neighbouring parts and turn array of those neighbouring parts
	 * 
	 * @return neighbouringArray - Array of neighburing arrays 
	 */
	public float[][] neighbouringArrays() {
		float[][] neighourArrays = new float[dataArray.length - 2][this.filter_size];

		for (int i = 0; i < dataArray.length-2; i++){
			neighourArrays[i] = subArray(this.dataArray, i, this.filter_size);				
		}
		return neighourArrays;
	}
	
	/**
	 * Method to get a slice of array from start- to end index 
	 * 
	 * @param array Original array
	 * @param start start index
	 * @param end end index
	 * @return slicedArray 
	 */
	private float[] subArray(float[] array, int start, int size){
		float[] slicedArray = new float[size];
		for(int i = 0; i < slicedArray.length; i++){
			slicedArray[i] = array[start + i]; 
		}
		return slicedArray;
	}

	/**
	 * Method to find the Median value in an array
	 *  
	 * @param array to check median in
	 * @return median value
	 */
	public static float median(float[] array) {
		float[] copyArray = Arrays.copyOf(array, array.length);
		Arrays.sort(copyArray);
		return copyArray[((copyArray.length + 1) / 2) - 1];
	}
}