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
		FilteringMethods.Type type = FilteringMethods.Type.SEQUENTIAL;
		filter.execute(type);
		filter.saveData(type, args[2]);

		// ParallelFiltering
		type = FilteringMethods.Type.PARALLEL;
		filter.execute(type);
		filter.saveData(type, args[2]);
	}
}