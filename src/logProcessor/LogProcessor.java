package logProcessor;

import java.io.*;

/***
 * LogProcessor is the main class which runs the whole operation to load the data and write to the output file.   
 ***/
public class LogProcessor {

	String protocolMappingFilepath;
	String lookupFilepath;
	String flowLogFilepath;
	String outputFilepath;
	
	public LogProcessor(String protocolMappingFilepath, String lookupFilepath, String flowLogFilepath, String outputFilepath) {
		
		this.protocolMappingFilepath = protocolMappingFilepath;
		this.lookupFilepath = lookupFilepath;
		this.flowLogFilepath = flowLogFilepath;
		this.outputFilepath = outputFilepath;
	}
	
	public void processLogs(){
		
		System.out.println("Starting to Process logs...");
		
		System.out.println("Loading protocol mapping from file: " + new File(this.protocolMappingFilepath).getAbsolutePath());
		
		ProtocolMapper protocolMapperObj = new ProtocolMapper(this.protocolMappingFilepath);
		
		protocolMapperObj.loadData();
		
		System.out.println("Successfully loaded protocol mapping.");
		
		
		System.out.println("Loading lookup tag mapping from file: " + new File(this.lookupFilepath).getAbsolutePath());
		
		LookupTableLoader lookupTableLoaderObj = new LookupTableLoader(this.lookupFilepath, protocolMapperObj);
		
		lookupTableLoaderObj.loadData();
		
		System.out.println("Successfully loaded lookup tag mapping.");
		

		System.out.println("Loading flow log data from file: " + new File(this.flowLogFilepath).getAbsolutePath());
		
		FlowLogDataLoader flowLogDataLoaderObj = new FlowLogDataLoader(this.flowLogFilepath, protocolMapperObj, lookupTableLoaderObj);
		
		flowLogDataLoaderObj.loadData();
		
		System.out.println("Successfully loaded flow log data.");
		
		System.out.println("Writing tag count and port-protocol count data to file: " + new File(this.outputFilepath).getAbsolutePath());
		
		OutputFileWriter outputFileWriterObj = new OutputFileWriter(this.outputFilepath);
		
		outputFileWriterObj.writeOutput(flowLogDataLoaderObj);
		
		System.out.println("Successfully written data to file");
		
		System.out.println("Log processing completed");
		
	}
	
	public static void main(String[] args) {
		String lookupFilepath = "resources//LookupData.csv";
        String flowLogFilepath = "resources//FlowLogData.txt";
        String outputFilepath = "Outputs//Output.txt";
        String protocolMappingFilepath = "resources//protocol-numbers.csv";
        
        LogProcessor logProcessorObj = new LogProcessor(protocolMappingFilepath, lookupFilepath, flowLogFilepath, outputFilepath);
        logProcessorObj.processLogs();

	}
}
