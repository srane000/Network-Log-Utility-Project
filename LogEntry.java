

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LogEntry {
	/**
	 * holds the private string variables that are in the file
	 */
	private final String sequence;
	private final String packetSize;
	private final String dateTime;
	private final String sourceIP;
	private final String destinationIP;
	private final String protocol;
	private final String comment;
	
	/**
	 * takes in all string parameters and uses an if statement to check if the values conform to what is expected.
	 * If the values are off then the exception is thrown with a print statement. Otherwise input values into the string.
	 * @param sequence
	 * @param packetSize
	 * @param dateTime
	 * @param sourceIP
	 * @param destinationIP
	 * @param protocol
	 * @param comment
	 */
	public LogEntry(String sequence, String packetSize, String dateTime, String sourceIP, String destinationIP, String protocol, String comment) {
		
		try {
			int seqInt = Integer.parseInt(sequence);
			int packInt = Integer.parseInt(packetSize);
		if(packInt <= 1 || packInt >= 1500 || seqInt<1 || seqInt > Integer.MAX_VALUE || dateTime.length() != 19 || sourceIP == null || destinationIP == null || protocol != "TCP" || protocol != "UDP")
			 throw new InstantiationException();
		}
		catch (InstantiationException ie) {
			System.out.print("One of more values are invalid");
		}
		
		this.sequence=sequence;
        this.packetSize=packetSize;
        this.dateTime=dateTime;
        this.sourceIP=sourceIP;
        this.destinationIP=destinationIP;
        this.protocol = protocol;
        this.comment=comment;
	}
	/**
	 * Includes 7 getter methods to access the private string variables.
	 * The date is arranged into a new format with a time and is parsed.
	 * The toString takes all strings and concatenates it into another string.
	 * 
	 */
	public String getSequence() {
		return sequence;
	}
	public String getDateTime() {
		return dateTime;
	}
	public Date getDateTimeAsDate() throws ParseException{
		SimpleDateFormat date1 = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
		Date list = date1.parse(dateTime);
		return list;
	}
	public String getSourceIP() {
		return sourceIP;
	}
	public String getDestinationIP() {
		return destinationIP;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getComment() {
		return comment;
	}
	public String getPacketSize() {
		return protocol;
	}
	public String toString() {
		String concat = sequence + "," + packetSize + "," + dateTime + "," + sourceIP + "," + destinationIP + "," + protocol + "," + comment;
		return concat;
	}
}
