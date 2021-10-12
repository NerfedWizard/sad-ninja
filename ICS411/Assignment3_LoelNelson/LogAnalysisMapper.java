import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogAnalysisMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text ip = new Text();
	static final String[] months = { " Jan", " Feb", " Mar", " Apr", " May",
			" Jun", " Jul", " Aug", " Sep", " Oct", " Nov", " Dec" };

	@Override
	/**@author Loel Nelson
	 * https://stackoverflow.com/questions/28646339/hadoop-finding-total-number-of-ip-hits-and-unique-ip-address-and-later-finding
	 * 
	 * The mapper for the mapReduce it is responsible for counting the image files and preparing it for the reducer to sum the totals 
	 * */
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		final String line = value.toString();
		if (line.contains("jpeg") || line.contains("jpg")) {
			context.getCounter(ImageCounter.JPEG).increment(1);
		} else if (line.contains("gif")) {
			context.getCounter(ImageCounter.GIF).increment(1);
		} else if (line.contains("images") || line.contains("img")
				&& !(line.contains("jpeg")) || !(line.contains("gif"))
				|| !(line.contains("jpg"))) {
			context.getCounter(ImageCounter.OTHER).increment(1);
		}
		// Getting the IP to send to reducer and grabbing the month of the
		// interaction for using the partition if enabled
		final String[] data = line.trim().split(" - ");
		if (data.length > 1) {
			String ipAddress = data[0];
			if (line.contains("Jan")) {
				ipAddress += " Jan";
			} else if (line.contains("Feb")) {
				ipAddress += " Feb";
			} else if (line.contains("Mar")) {
				ipAddress += " Mar";
			} else if (line.contains("Apr")) {
				ipAddress += " Apr";
			} else if (line.contains("May")) {
				ipAddress += " May";
			} else if (line.contains("Jun")) {
				ipAddress += " Jun";
			} else if (line.contains("Jul")) {
				ipAddress += " Jul";
			} else if (line.contains("Aug")) {
				ipAddress += " Aug";
			} else if (line.contains("Sep")) {
				ipAddress += " Sep";
			} else if (line.contains("Oct")) {
				ipAddress += " Oct";
			} else if (line.contains("Nov")) {
				ipAddress += " Nov";
			} else if (line.contains("Dec")) {
				ipAddress += " Dec";
			}
			ip.set(ipAddress);
			context.write(ip, one);
		}
	}
}