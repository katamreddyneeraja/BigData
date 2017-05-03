#  Analyse customer feedback stored in text file about mobile phone and separate out positive & negative feedback in separate files
Problem statement:- Analyse text file storing customer feedback about various mobile phone from various vendor using mapreduce and separate out positive & negative comments in separate file corresponding to each mobile phone with price.And corresponding to each mobile set display total number of comments too.Download sample input file.

Input schema :- <Mobile_set_detail><TAB><Price><TAB><Vendor><TAB><Comment>
Example:-  Lenovo A6000 Plus Rs. 7,499.00 Flipkart Satisfied with  phone.

Expected output:- 
In positiveFeedback_file : <Mobile_detail><TAB><Comment_count><TAB><All_comments_separated by ||>
Apple Iphone 4s - 16 Gb:Rs. 12,243.00 Comments(2) Amazingly smooth and has a much better battery life. || good for style and long term uses. || 
 In negativeFeedback_file : <Mobile_detail><TAB><Comment_count><TAB><All_comments_separated by ||>
Lenovo VIBE P1m (Black, 16 GB):Rs. 7,999 Comments(3)  Poor service so do not buy. ||Poor service so do not buy. ||Do not prefer and not reccomend ||



-----------
Detailed explanation : http://www.devinline.com/2015/12/mapreduce-analyse-customer-feedback.html