import java.io.IOException;

/**
 * Driver class to run Program 
 */
public class Program {

	public static void main(String[] args) throws IOException {
		FilteringMethods filter = new FilteringMethods();
		filter.loadData("sampleInput/" + args[0]);
		filter.process(Integer.parseInt(args[1]));
		
		// SequentaialFiltering
		System.gc();
		FilteringMethods.Type type = FilteringMethods.Type.SEQUENTIAL;
		long seqTime = System.nanoTime();
		filter.execute(type);
		long seq = System.nanoTime() - seqTime;
		System.out.println((double)seq/1000000000.0);
		//filter.saveData(type, args[2]);

		// ParallelFiltering
		System.gc();
		type = FilteringMethods.Type.PARALLEL;
		long parTime = System.nanoTime();
		filter.execute(type);
		long par = System.nanoTime() - parTime;
		System.out.println((double)par/1000000000.0);
		//filter.saveData(type, args[2]);

	}
}