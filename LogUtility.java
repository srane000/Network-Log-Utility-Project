

import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;


public class LogUtility {
	/**
	 * An arraylist is created for the entries and reads the following files.
	 * Reads each line and places it into the arraylist. If the line is inconsistent
	 * it throws and error. Ensures that the scanner class is closed.
	 */
	private final ArrayList<LogEntry> listLogEntries;
	public LogUtility(){
		listLogEntries = new ArrayList<LogEntry>();
		
	}
	
	public boolean parseFile(String fileName) {
		File file = new File(fileName);
		
		try {
		Scanner fileinput = new Scanner(file);
		while(fileinput.hasNextLine()) {
			String line = fileinput.nextLine();
			String[] input = line.split(",");
			try {
				LogEntry log = new LogEntry(input[0], input[1], input[2], input[3], input[4], input[5], input[6]);
				listLogEntries.add(log);
			}
			catch (Exception ie) {
				System.out.printf("Skipping Line: %s%n", line);
			}
		}
		fileinput.close();
		return true;
		
		}
		catch(FileNotFoundException ab) {
			return false;
		}
	}
		/**
		 * Gets the number of entries in listLogEntries
		 * @return the entries in one concatenated string
		 */
		public String toString() {
			String a = "LogUtility: There are ";
			String b = " records.";
			return (a + listLogEntries.size() + b);
		}
		
		/**
		 * Returns a list of records between the date string parameters.
		 * Two parameters includes the start and end dates which are converted to Date.
		 * @param x startdate
		 * @param y enddate
		 * @return arraylist aList
		 * @throws ParseException incase there is inconsistencies
		 */
		public ArrayList<LogEntry> getBetween(String x, String y) throws ParseException{
			ArrayList<LogEntry> aList = new ArrayList<LogEntry>();
			SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyy HH:mm:ss");
				Date startDate = formatter.parse(x);
				Date endDate = formatter.parse(y);
				
				for (LogEntry log : listLogEntries) {
					Date dateTime = formatter.parse(log.getDateTime());
				
					if(dateTime.compareTo(startDate) >= 0 && dateTime.compareTo(endDate) <= 0) {
						aList.add(log);
					}
					
				}
				return aList;
		    }
		
		/**
		 * returns the first time the field pops up that matches the search criteria.
		 * Common method that has search subroutine.
		 * @param value parameter
		 * @param field parameter
		 * @return
		 */
		public LogEntry findFirst(String value, String field) {
			for(LogEntry log : listLogEntries) {
				String val;
				
				switch (field) {
				case "sequence":
					val = log.getSequence();
					break;
				case "dateTime":
					val = log.getDateTime();
					break;
				case "packetSize":
					val = log.getPacketSize();
					break;
				case "sourceIP":
                    val = log.getSourceIP();
                    break;
                case "destinationIP":
                    val = log.getDestinationIP();
                    break;
                case "protocol":
                    val = log.getProtocol();
                    break;
                case "comment":
                    val = log.getComment();
                    break;
                default:
                    val = null;
			}
			if (val.equals(value)) {
				return log;
			}
		}
			return null;
		}
		
		
		 /**
	     * @param a searches for a sequence
	     * @return the sequence
	     */
	    public LogEntry findFirstId(String a){
	        return findFirst(a,"sequence");
	    }
	    
	    /**
	     * @param a finds a source ip
	     * @return the source ip address
	     */
	     public LogEntry findFirstSourceIP(String a){
	        return findFirst(a,"sourceIP");
	    }
	     /**
		     * @param a finds a date
		     * @return the date and time
		     */
		    public LogEntry FindFirstDateTime(String a){
		        return findFirst(a,"dateTime");
		    }
	     /**
	      * @param a finds a destination ip
	      * @return the destination ip address
	      */
	     public LogEntry FindFirstdestinationIP(String a){
	        return findFirst(a,"destinationIP");
	    }
	     /**
	      * @param a finds the protocol TCP or UDP
	      * @return the protocol found
	      * @return 
	      */
	     public LogEntry FindFirstprotocol(String a){
	        return findFirst(a,"portocol");
	    }
	     /**
	      * @param a find a comment associated
	      * @return the comment
	      */
	     public LogEntry FindFirstComment(String a){
	        return findFirst(a,"comment");
	    }
}

		
		
		
		
		
		

