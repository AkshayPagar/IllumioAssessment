package logProcessor;

import java.io.*;
import java.util.*;

/***
 * ProtocolMapper gets the data for protocol mapping and creates a Map with protocol name - protocol number and vice versa.  
 * The data file is specified with 'filepath' variable which can be configured in this class.
 ***/
public class ProtocolMapper {

	//Maps different protocols with their target code
	private String filepath;
	private Map<String, String> protocolNumMapping;
	private Map<String, String> protocolNameMapping;
	
	public ProtocolMapper(String protocolMappingFile) {
		
		protocolNumMapping = new HashMap<String, String>();
		protocolNameMapping = new HashMap<String, String>();
		this.filepath = protocolMappingFile;

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
                String[] protocolData = entry.split(",");
                if(protocolData.length > 1) {
                	String protocolNum = protocolData[0];
                	String protocolName = protocolData[1].trim().toLowerCase();
                	this.protocolNumMapping.put(protocolName, protocolNum);
                	this.protocolNameMapping.put(protocolNum, protocolName);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading protocol mapping file: " + e.getMessage());
        }
		
	}
	
	// Get the protocol number when protocol name provided or return with default value 'protocolName'  
	public String getProtocolNumMapping(String protocolName) {
		
		return protocolNumMapping.getOrDefault(protocolName, protocolName);
	
	}
	
	// Get the protocol name when protocol number provided or return with default value ' '
	public String getProtocolNameMapping(String protocolNum) {
		
		return protocolNameMapping.getOrDefault(protocolNum, "");
		
	}
	
}
