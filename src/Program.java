import java.io.IOException;

/**
 * Driver class to run Program 
 */
public class Program {

	public static void main(String[] args) throws IOException {
		FilteringMethods filter = new FilteringMethods();
		filter.loadData(args[0]);
		
		// SequentaialFiltering
		FilteringMethods.Type type = FilteringMethods.Type.SEQUENTIAL;
		filter.process(Integer.parseInt(args[1]));
		filter.saveData(type, args[2]);

		// ParallelFiltering
		type = FilteringMethods.Type.PARALLEL;
		filter.process(Integer.parseInt(args[1]));
		filter.saveData(type, args[2]);
	}
}