import java.io.IOException;

/**
 * Driver class to run Program 
 */
public class Program {

	public static void main(String[] argsa) throws IOException {
		FilteringMethods filter = new FilteringMethods();
		String[] args = {"sampleInput1000.txt", "3", "sampleOutput1000.txt"};
		filter.loadData("sampleInput/" + args[0]);
		
		// SequentaialFiltering
		FilteringMethods.Type type = FilteringMethods.Type.SEQUENTIAL;
		filter.process(Integer.parseInt(args[1]));
		filter.execute(type);
		filter.saveData(type, args[2]);

		// ParallelFiltering
		type = FilteringMethods.Type.PARALLEL;
		filter.process(Integer.parseInt(args[1]));
		filter.execute(type);
		filter.saveData(type, args[2]);
	}
}