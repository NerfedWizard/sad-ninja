import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Loel Nelson
 * Partitioner for separating the keys by month of the year 0 being Jan 1 Feb and so. 
 */

public class LogAnalysisPartitioner extends Partitioner<Text, IntWritable>
		implements Configurable {

	private Configuration conf = null;

	@Override
	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public Configuration getConf() {
		return conf;
	}

	/**
	 * Partitioning the Data across 12 partitions based on Months of the Year
	 */
	public int getPartition(Text key, IntWritable value, int taskReduce) {
		String partition = key.toString();
		if (partition.contains("Jan")) {
			return 0;
		} else if (partition.contains("Feb")) {
			return 1;
		} else if (partition.contains("Mar")) {
			return 2;
		} else if (partition.contains("Apr")) {
			return 3;
		} else if (partition.contains("May")) {
			return 4;
		} else if (partition.contains("Jun")) {
			return 5;
		} else if (partition.contains("Jul")) {
			return 6;
		} else if (partition.contains("Aug")) {
			return 7;
		} else if (partition.contains("Sep")) {
			return 8;
		} else if (partition.contains("Oct")) {
			return 9;
		} else if (partition.contains("Nov")) {
			return 10;
		} else {
			return 11;
		}
	}
}