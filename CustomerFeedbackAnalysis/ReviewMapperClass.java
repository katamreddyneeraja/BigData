/*
* Mapper executes setup for each task in sequence : setup - > map -> cleanup
*/
class ReviewMapperClass extends Mapper<Object, Text, Text, Text> {
@Override
protected void map(Object key, Text value, Context context) {
 try {
  String inputLine = value.toString();
  String feedback = inputLine.split("\\t")[3];
  String productId = inputLine.split("\\t")[0];
  String price = inputLine.split("\\t")[1];
  String mapperKey = productId + ":" + price;
  context.write(new Text(mapperKey), new Text(feedback));
 } catch (IOException e) {
  e.printStackTrace();
 } catch (InterruptedException e) {
  e.printStackTrace();
 }
}
}