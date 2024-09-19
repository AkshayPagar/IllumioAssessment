# Illumio Technical Assessment

## Problem Statement

Write a program which can parse a file containing flow log data and maps each row to a tag based on a lookup table. The Lookup table is defined in a csv file, and it has 3 columns dstport, protocol and tag. The dstport and protocol combination decides what tag can be applied. 

## Solution
This solution is Java based where the program loads input files i.e. Flow log data txt and Lookup table csv and protocol and their number mapping csv and is able to process these logs and data to assign each row with a tag and count the occurrences of each tag and also the protocol-port combinations. 

## Tools Required

- Java : JDK 8 or higher

## Assumptions

- Log files are formatted according to the problem statement provided i.e. only version 2 flow log data.
- Protocol mappings are mapped using standard mapping csv from the source - 
- I have used standard flow log format from the link provided : 
- Lookup table tags are considered as case-insensitive and matched accordingly.

## Running the application: 

Please follow below steps to run the application

### 1. Clone the git repository 
``` 
git clone https://github.com/AkshayPagar/IllumioAssessment.git
```
### 2. Go to project directory and make sure if all the resource files are present in resources directory. If needed replace the files with updated data. 
1. Flow Log txt
2. Lookup table csv
3. Protocol numbers csv
```
cd IllumioAssessment/src
ls -la resources 
```
### 3. Compile and run the code.
```
javac -cp . logProcessor/*.java
java logProcessor.LogProcessor
```
   
### 4. Check the output data
The output file with tagCount and portProtocolCount is created in the Outputs directory. Verify the data. 


<<<<<<< Updated upstream
Thank you for the opportunity for the assessment. I enjoyed working on the assignment. 
=======
## Testing 

I was able to test code with unit testing of separate modules by running single functionality of the workflow. 
1. Protocol-mapping loading and Fetching protocol number as per protocol. 
2. Lookup table loading and fetching tags based on dstport and protocol. 
3. Flow log data loading and verifying tagCount map and portProtocolCount map. 

I tested the code by changing input data and adding more data, verified the updated data is reflected in the output file. 

Thank you for the opportunity for the assessment. I enjoyed working on the assignment. 
>>>>>>> Stashed changes
