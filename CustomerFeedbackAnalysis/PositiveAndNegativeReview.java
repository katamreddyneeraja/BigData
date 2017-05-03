//Driver class file.
public class PositiveAndNegativeReview {
public static String positiveReview = "positiveReview";
public static String negativeReview = "negativeReview";

/**
 * Uses of setUp and cleanup in Mapper and Reducer - 
 */
public static void main(String[] args) {
 final String POSITIVE_WORD = "good |satisfied |classic|class|happy |thanks |
  recommend |good to go|best |rocking |yo |fancy |stylish |must buy |
  amazing |smooth |awesome |damn good ";
 final String NEGATIVE_WORD = "not good |Do not |donot |poor |
  not satisfied |very poor|not happy |worst |
  not recommend |do noy buy|not-satisfied|waste |bad |
  false |not stylish |should not buy |not amazing |
  not smooth |wasted |damn bad ";

 Configuration conf = new Configuration();
 conf.set("positiveWords", POSITIVE_WORD);
 conf.set("negativeWords", NEGATIVE_WORD);
 try {
  Job job = Job.getInstance(conf, "Filer file with good feedback!!");
  job.setMapperClass(ReviewMapperClass.class);
  job.setReducerClass(ReviewReducerClass.class);
  job.setJarByClass(ReviewFilterForBestBuy.class);
  /*
   * Set below four property carefully otherwise job fails silently
   * after first context.write
   */
  job.setMapOutputKeyClass(Text.class);
  job.setMapOutputValueClass(Text.class);
  job.setOutputKeyClass(Text.class);
  job.setOutputValueClass(Text.class);

  /* Optional, it's good to set */
  job.setInputFormatClass(TextInputFormat.class);
  job.setOutputFormatClass(TextOutputFormat.class);

  /* Multiple output setting */
  MultipleOutputs.addNamedOutput(job, negativeReview,
    TextOutputFormat.class, Text.class, Text.class);
  MultipleOutputs.addNamedOutput(job, positiveReview,
    TextOutputFormat.class, Text.class, Text.class);

  Path pathInput = new Path(
  "hdfs://localhost:54310/user/hduser1/feedbackPosNeg.txt");
  Path pathOutputDir = new Path(
  "hdfs://localhost:54310/user/hduser1/testfs/output_dir_feedback");
  FileInputFormat.setInputPaths(job, pathInput);
  FileOutputFormat.setOutputPath(job, pathOutputDir);
  System.exit(job.waitForCompletion(true) ? 1 : 0);
 } catch (IOException e) {
  e.printStackTrace();
 } catch (ClassNotFoundException e) {
  e.printStackTrace();
 } catch (InterruptedException e) {
  e.printStackTrace();
 }
}
}