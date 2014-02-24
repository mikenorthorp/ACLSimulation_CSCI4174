/**
 * This is an ACL Simulation that reads in an ACL list and a list of packets,
 * then prints out which are allowed and denied.
 */

// Import for file read
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ACLSimulation {
    public static void main(String args[]) {
        // Read in a file name
        String filename = "";

        // Set up scanner
        Scanner scan = new Scanner(System.in);
        // Read in the ACL file and store each line in a rule list to check against
        System.out.print("Please enter the ACL Filename to read in: ");
        filename = scan.next();

        // Flush input
        scan.nextLine();

        // Array list of ACLRules
        ArrayList<ACLRule> aclList = new ArrayList<ACLRule>();

        // Wrap the file read in a try catch to catch errors
        // File read modifed from http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml
        try {
            // Set up file input stream
            FileInputStream input = new FileInputStream(filename);
            // Set up the datainput stream and buffered reader
            DataInputStream dataIn = new DataInputStream(input);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dataIn));

            // Temp line to read in
            String tempLine;
            boolean endRead = false;
            // Read File Line By Line while not null and reading not stopped
            while ((tempLine = buffer.readLine()) != null && !endRead)   {
                // Create a tokenizer for this line
                StringTokenizer tokenizer = new StringTokenizer(tempLine);
                String current = "";
                int count = 0;
                // Loop through tokens in line
                while (tokenizer.hasMoreTokens() && !endRead) {
                    current = tokenizer.nextToken();

                    // Check if current line is end of ACL rules
                    if(current == "interface") {
                        endRead = true;
                    } else if (count == 3) {
                        // Deny or allow
                    } else if (count == 4) {
                        // Protocol or
                    } else if (count == 5) {
                        //
                    }

                }
            }
            // Close the data input stream
            dataIn.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }


        // Read in the packet list file and store in an array list of packets
        System.out.print("Please enter the Packet list to read in: ");
        filename = scan.next();

        // Flush input
        scan.nextLine();

        // Array list of ACLRules
        ArrayList<Packet> packetList = new ArrayList<Packet>();

        // Wrap the file read in a try catch to catch errors
        // File read modified from http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml
        try {
            // Set up file input stream
            FileInputStream input = new FileInputStream(filename);
            // Set up the datainput stream and buffered reader
            DataInputStream dataIn = new DataInputStream(input);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dataIn));

            // Temp line to read in
            String tempLine;
            //Read File Line By Line
            while ((tempLine = buffer.readLine()) != null)   {
                // Print the content on the console
                System.out.println (tempLine);
            }
            // Close the data input stream
            dataIn.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        // Determine what to deny and allow by going through the rule list for each packet, then printing out a
        // deny or allow

    }
}
