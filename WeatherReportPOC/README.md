# Weather report POC - MapReduce program to analyse time-temperature statistics and generate report with max/min temperature

Problem Statement: 
1. The system receives temperatures of various cities(Austin, Boston,etc) of USA captured at regular intervals of time on each day in an input file. 
2. System will process the input data file and generates a report with Maximum and Minimum temperatures of each day along with time. 
3. Generates a separate output report for each city. 
Ex: Austin-r-00000 
 Boston-r-00000 
 Newjersy-r-00000 
 Baltimore-r-00000 
 California-r-00000 
 Newyork-r-00000 
 
 Expected output:- In each output file record should be like this:
25-Jan-2014 Time: 12:34:542 MinTemp: -22.3 Time: 05:12:345 MaxTemp: 35.7