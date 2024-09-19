package logProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * LookupTableLoader gets the data from lookup data csv and creates a Map with dstport-protocolNum -> tag  
 * The data file is specified with 'filepath' variable which can be configured in this class.
 ***/
public class LookupTableLoader {

	private String filepath;
	private Map<String, String> lookupTableMapping;
	private ProtocolMapper protocolMapper;

	public LookupTableLoader(String LookupTableFilepath, ProtocolMapper protocolMapper) {

		lookupTableMapping = new HashMap<String, String>();
		this.filepath = LookupTableFilepath;
		this.protocolMapper = protocolMapper;
		
	}
	
	public void setProtocolMapper(ProtocolMapper protocolMapper) {
		
		this.protocolMapper = protocolMapper;
	
	}
	
	public ProtocolMapper getProtocolMapper() {
	
		return this.protocolMapper;
		
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
	        while ((entry = br.readLine()) != null) {
	            String[] lookupData = entry.split(",");
	            if(lookupData.length == 3) {
	            	String dstport = lookupData[0];
	            	String protocolName = protocolMapper.getProtocolNumMapping(lookupData[1].trim().toLowerCase());
	            	String tag = lookupData[2];
	            	String lookupKey = dstport + "-" + protocolName;
	            	lookupTableMapping.put(lookupKey, tag);
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Error while reading Lookup table file: " + e.getMessage());
	    }	
		
	}
	
	// Gets the tag data based on the key(dstport-protocolNum) provided or default value "untagged" 
	public String getLookupDataEntry(String key) {
		
		return lookupTableMapping.getOrDefault(key, "untagged");
	
	}
	
}
