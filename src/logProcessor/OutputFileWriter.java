package logProcessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/***
 * OutputFileWriter writes the tag counts and port-protocol counts to the output file provided. 
 * The output file is specified with 'filepath' variable which can be configured in this class.
 ***/
public class OutputFileWriter {

	private String filepath;
	
	public OutputFileWriter(String outputFilepath) {
		
		this.filepath = outputFilepath; 
	
	}
	
	public void setFilepath(String filepath) {
		
		this.filepath = filepath;
		
	}
	
	public String getFilePath() {
	
		return this.filepath;
		
	}
	
	public void writeOutput(FlowLogDataLoader flowLogDataLoaderObj) {
		
		Map<String, Integer> tagCountMap = flowLogDataLoaderObj.getTagCountMap();
		Map<String, Integer> protocolCountMap = flowLogDataLoaderObj.getProtocolCountMap();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filepath))) {
            
			bw.write("***** Tag Counts *****\n");
			
			bw.write("Tag, Count \n");
			
			for(String tag: tagCountMap.keySet()) {
				bw.write(tag + ", " + tagCountMap.get(tag) + "\n");
			}
			
			bw.write("\n***** Port-Protocol Counts *****\n");
			
			bw.write("Port, Protocol, Count \n");
			
			for(String protocol: protocolCountMap.keySet()) {
				String[] data = protocol.split("-");
				
				String port = data[0];
				String protocolName = data[1];
				
				bw.write(port + ", " + protocolName + ", " + protocolCountMap.get(protocol) + "\n");
			}
        } catch (IOException e) {
            System.err.println("Error while writing output file: " + e.getMessage());
        }
    }
}
