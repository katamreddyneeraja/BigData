/*
* Reducer executes on mapper output in sequence : setup - > map -> cleanup we
* have not overridden setup and cleanup.
*/
class ReviewReducerClass extends Reducer<Text, Text, Text, Text> {
MultipleOutputs<Text, Text> multiOutput;
List<String> wordList = new LinkedList<String>();

@Override
protected void setup(Context context) {
 multiOutput = new MultipleOutputs<Text, Text>(context);
 Configuration conf = context.getConfiguration();
 wordList.add(conf.get("positiveWords"));
 wordList.add(conf.get("negativeWords"));
}

@Override
public void reduce(Text key, Iterable<Text> feedbackList, Context con) {
 Matcher matcherQualifyPositive;
 Matcher matcherQualifyNegative;
 final String POS_QUALIFY_PATTERN = "(?)(.*)(" + wordList.get(0)
   + ")(.*)";
 final String NEG_QUALIFY_PATTERN = "(?)(.*)(" + wordList.get(1)
   + ")(.*)";
 Pattern posQualifyPattern = Pattern.compile(POS_QUALIFY_PATTERN,
   Pattern.CASE_INSENSITIVE);
 Pattern negQualifyPattern = Pattern.compile(NEG_QUALIFY_PATTERN,
   Pattern.CASE_INSENSITIVE);

 int countPos = 0;
 int countNeg = 0;
 try {
  StringBuffer sbfPos = new StringBuffer("");
  StringBuffer sbfNeg = new StringBuffer("");
  for (Text strVal : feedbackList) {
   matcherQualifyPositive = posQualifyPattern.matcher(strVal
     .toString());
   matcherQualifyNegative = negQualifyPattern.matcher(strVal
     .toString());
   if (matcherQualifyPositive.find()) {
    if (!matcherQualifyNegative.find()) {
     sbfPos.append(strVal).append(" || ");
     countPos++;
    }
   } else if (matcherQualifyNegative.find()) {
    sbfNeg.append(strVal).append("||");
    countNeg++;
   }
  }
  /* Write on both positive and negative feedback file */
  if (countPos != 0 && !sbfPos.equals("")) {
   multiOutput.write(PositiveAndNegativeReview.positiveReview,
   new Text(key.toString() + " Comments("+ countPos + ")"),
     new Text(sbfPos.toString()));
  }
  if (countNeg != 0 && !sbfNeg.equals("")) {
   multiOutput.write(PositiveAndNegativeReview.negativeReview,
   new Text(key.toString() + " Comments("+ countNeg + ")"),
     new Text(sbfNeg.toString()));
  }
  System.out.println(sbfNeg.toString());
  System.out.println(sbfPos.toString());
 } catch (IOException e) {
  e.printStackTrace();
 } catch (InterruptedException e) {
  e.printStackTrace();
 }
}

@Override
protected void cleanup(Context context) {
 wordList = null;
 multiOutput = null;
}
}