import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Parallel Filtering class that uses the Java Fork/Join framework to parallelize the median filter operation using a divide-and-conquer algorithm.
 * 
 * @author TWLCOM001 - Comfort Twala
 * @version 1.0
 */

public class ParallelFiltering extends RecursiveTask<float[]> {
	private float[][] dataArray;
	private float[] resultArray;
	private int start, end;
	private final int SEQ_THRESHOLD = 500;

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
	public ParallelFiltering(float[][] data, int start, int end) {
		this.dataArray = data;
		this.start = start;
		this.end = end;
	}
	

	/**
	 * RecursiveTask overridden method to run parallel computing using the divide and conquer algorithm
	 */
	@Override
	protected float[] compute() {
		resultArray = new float[dataArray.length+2];
		resultArray[0] = dataArray[0][0];
		if ((end - start) < SEQ_THRESHOLD){
			for (int i = 0; i < dataArray.length; i++){
				resultArray[i+1] = arrayHandler.median(dataArray[i]);
			}
			resultArray[resultArray.length-1] = dataArray[dataArray.length-1][dataArray[dataArray.length-1].length-1];
			return resultArray;
		} else {
			int half = (start + end) / 2;
			ParallelFiltering filterA = new ParallelFiltering(dataArray, start, half);
			ParallelFiltering filterB = new ParallelFiltering(dataArray, half, end);

			filterA.fork();
			filterB.fork();
			float[] A = filterA.join();
			float[] B = filterB.join();
			float[] res = Arrays.copyOf(A, A.length + B.length);
			System.arraycopy(B, 0, res, A.length, B.length);			
			return res;
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

}
