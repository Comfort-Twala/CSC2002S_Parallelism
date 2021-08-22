// import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
// import java.util.concurrent.RecursiveTask;

/**
 * Parallel Filtering class that uses the Java Fork/Join framework to parallelize the median filter operation using a divide-and-conquer algorithm.
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */

public class ParallelFiltering extends RecursiveAction{
	private float[][] dataArray;
	private ArrayList<Float> resultArray;
	private int start, end;
	private int SEQ_THRESHOLD = 6000000;

	/**
	 * Constructor to create ParallelFiltering instance
	 */
	public ParallelFiltering() {}

	/**
	 * Constructor to create ParallelFiltering instance with data, starting- and ending point
	 * 
	 * @param data to be filtered
	 * @param start point of array
	 * @param end point of array
	 */
	public ParallelFiltering(float[][] data, int start, int end, ArrayList<Float> result) {
		this.dataArray = data;
		this.start = start;
		this.end = end;
		this.resultArray = result;
	}
	

	/**
	 * RecursiveTask overridden method to run parallel computing using the divide and conquer algorithm
	 */
	@Override
	protected void compute() {
		resultArray = new ArrayList<Float>();
		if ((end - start) < SEQ_THRESHOLD){
			for (int i = 0; i < dataArray.length; i++){
				resultArray.add(arrayHandler.median(dataArray[i]));
			}
		} else {
			int half = (start + end) / 2;
			invokeAll(
				new ParallelFiltering(dataArray, start, half, resultArray),
				new ParallelFiltering(dataArray, half, end, resultArray)
			);
		}
	}

	/**
	 * Set the dataArray, start position and ending point
	 * 
	 * @param dataArray
	 */
	public void setDataArray(float[][] dataArray) {
		this.dataArray = dataArray;
		this.start = 0;
		this.end = dataArray.length;
	}

	/**
	 * Set the Sequential Treshold 
	 * 
	 * @param threshold
	 */
	public void setThreshold(int threshold) {
		this.SEQ_THRESHOLD = threshold;
	}

	public ArrayList<Float> getResult() {
		return resultArray;
	}
}
