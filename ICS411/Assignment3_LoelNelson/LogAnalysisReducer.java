import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author Loel Nelson Reducer to get the key value pairs and sum the number for
 *         each ip and write it to file the results
 */
public class LogAnalysisReducer extends

Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
		}
		//Stripping the Month off ip
		String[] data = (key.toString()).trim().split(" ");
		String temp = data[0];
		key.set(temp);
		context.write(key, new IntWritable(sum));
	}
}