import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.CounterGroup;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author Loel Nelson Driver for the mapreduce program it is used for creating
 *         the job and setting classes and the details. This program takes the
 *         internet traffic and counts the hits made by each ipaddress. If all
 *         the classes are set to on it will not only count the ip's but also
 *         put them in different partitions by the month.
 * */
public class LogAnalysisDriver {
	public static void main(String[] args) throws Exception {
		Job job = new Job();
		job.setJarByClass(LogAnalysisDriver.class);
		job.setJobName("Log Analysis");// Creating Job Name

		// to accept the hdfs input and output dir at run time
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// Partitioner
		job.setPartitionerClass(LogAnalysisPartitioner.class);
		job.setNumReduceTasks(12);

		// Mapper/Reducer
		job.setMapperClass(LogAnalysisMapper.class);
		job.setReducerClass(LogAnalysisReducer.class);

		// Combiner
//		job.setCombinerClass(LogAnalysisCombiner.class);

		// setting the output data type classes
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);

		// Getting Counters
		Counters counter = job.getCounters();

		// Getting specific counters code adapted from
		// https://acadgild.com/blog/counters-in-mapreduce
		Counter jpeg = counter.findCounter(ImageCounter.JPEG);
		System.out.println(jpeg.getDisplayName() + " : " + jpeg.getValue());
		Counter gif = counter.findCounter(ImageCounter.GIF);
		System.out.println(gif.getDisplayName() + " : " + gif.getValue());
		Counter other = counter.findCounter(ImageCounter.OTHER);
		System.out.println(other.getDisplayName() + " : " + other.getValue());

		for (CounterGroup group : counter) {
			System.out.println("* Counter Group: " + group.getDisplayName()
					+ " (" + group.getName() + ") ");
			for (Counter count : group) {
				System.out.println(" - " + count.getDisplayName() + ": "
						+ count.getName() + ": " + count.getValue());
			}
		}
	}

}