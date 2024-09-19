package logProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * FlowLogDataLoader gets the data from Flow log data and creates a Maps for tagCounts and protocolPortCounts with respective counts.   
 * The data file is specified with 'filepath' variable which can be configured in this class.
 ***/
public class FlowLogDataLoader {
		
	
	private String filepath;
	private ProtocolMapper protocolMapper;
	private LookupTableLoader lookupTableLoaderObj;
	Map<String, Integer> tagCountMap;
    Map<String, Integer> protocolCountMap;
	
	public FlowLogDataLoader(String flowLogFilepath, ProtocolMapper protocolMapperObj, LookupTableLoader lookupTableLoaderObj) {
		
		tagCountMap = new HashMap<String, Integer>();
		protocolCountMap = new HashMap<String, Integer>();	
		this.filepath = flowLogFilepath;
		this.protocolMapper = protocolMapperObj;
		this.lookupTableLoaderObj = lookupTableLoaderObj;
		
	}
	
	public void setProtocolMapper(ProtocolMapper protocolMapper) {
		
		this.protocolMapper = protocolMapper;
	
	}
	
	public ProtocolMapper getProtocolMapper() {
	
		return this.protocolMapper;
		
	}
	
	public void setLookupTableLoaderObject(LookupTableLoader lookupTableLoaderObj) {
		
		this.lookupTableLoaderObj = lookupTableLoaderObj;
	
	}
	
	public LookupTableLoader getLookupTableLoaderObject() {
	
		return this.lookupTableLoaderObj;
		
	}
	
	public void setFilepath(String filepath) {
		
		this.filepath = filepath;
		this.loadData();
	
	}
	
	public String getFilePath() {
	
		return this.filepath;
		
	}
	
	public void loadData() {
		
		try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
			String entry;
			String protocolName= "";
			while ((entry = br.readLine()) != null) {
				String[] parts = entry.split("\\s+");
				if (parts.length > 7) { 
				String dstPort = parts[5];
				String protocol = parts[7].trim().toLowerCase(); 
				
				String flowLogKey = dstPort + "-" + protocol;
				
				String tag = lookupTableLoaderObj.getLookupDataEntry(flowLogKey);
				tagCountMap.put(tag, tagCountMap.getOrDefault(tag, 0) + 1);
				
				protocolName = protocolMapper.getProtocolNameMapping(protocol);
				protocolCountMap.put(dstPort + "-" + protocolName, protocolCountMap.getOrDefault(flowLogKey, 0) + 1);
			}
			}
		} catch (IOException e) {
			System.err.println("Error reading flow log file: " + e.getMessage());
		}
		
	}
	
	//Gets the tagCount map 
	public Map<String, Integer> getTagCountMap(){
		
		return tagCountMap;
		
	}
	
	//Gets the protocolCount map
	public Map<String, Integer> getProtocolCountMap(){
		
		return protocolCountMap;
		
	}
	
}
