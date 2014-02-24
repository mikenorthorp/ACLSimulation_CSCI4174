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
            boolean isRule = false;
            // Read File Line By Line while not null and reading not stopped
            while ((tempLine = buffer.readLine()) != null)   {
                // Create a tokenizer for this line
                StringTokenizer tokenizer = new StringTokenizer(tempLine);
                String current = "";
                // Set count to 0 and make sure it is not a rule until found in while loop
                int count = 0;
                isRule = false;
                boolean isExtended = false;

                // Variables for rule parts
                IPAddress source = null;
                IPAddress sourceMask = null;
                IPAddress dest = null;
                IPAddress destMask = null;
                boolean allow = false;
                int port = -1;
                String protocol = null;

                // Loop through tokens in line to get rule stuff
                while (tokenizer.hasMoreTokens()) {
                    current = tokenizer.nextToken();

                    // Check if first line is access-list, then continue getting parts of it
                    if(current.equals("access-list")) {
                        isRule = true;
                    }

                    // Parse rule if it is a rule
                    if(isRule) {
                        // Check if standard or extended by checking the acl-number
                        if (count == 1) {
                            if(Integer.parseInt(current) > 99) {
                                isExtended = true;
                            } else {
                                isExtended = false;
                            }
                        }
                        // Logic for extended ACL
                        if(isExtended) {
                            // Permit / Deny
                            if (count == 2) {
                                if(current.equals("deny")) {
                                    allow = false;
                                } else {
                                    allow = true;
                                }
                                // Protocol
                            } else if (count == 3) {
                                protocol = current;
                                // Source
                            } else if (count == 4) {
                               source = new IPAddress(current);
                                // Source mask
                            } else if (count == 5) {
                                sourceMask = new IPAddress(current);
                                // Dest
                            } else if (count == 6) {
                                dest = new IPAddress(current);
                                // Dest Mask
                            } else if (count == 7) {
                                destMask = new IPAddress(current);
                                // Port number
                            } else if (count == 9) {
                                port = Integer.parseInt(current);
                            }

                            // Add extended rule without port
                            if (destMask != null && port == -1 && count == 9) {
                                // End of rule, rule complete so add it to rule list of standard style
                                ACLRule newRule = new ACLRule(source, sourceMask, dest, destMask, allow, protocol);

                                // Add to ACL Rule list
                                aclList.add(newRule);

                                // Add extended rule with port
                            } else if (destMask != null && count == 9) {
                                // End of rule, rule complete so add it to rule list of standard style
                                ACLRule newRule = new ACLRule(source, sourceMask, dest, destMask, allow, protocol, port);

                                // Add to ACL Rule list
                                aclList.add(newRule);
                            }
                        } else { // Logic for standard ACL
                            // Permit / Deny
                            if (count == 2) {
                                if(current.equals("deny")) {
                                    allow = false;
                                } else {
                                    allow = true;
                                }
                            // Source IP
                            } else if (count == 3) {
                                source = new IPAddress(current);
                            // Source mask
                            } else if (count == 4) {
                                sourceMask = new IPAddress(current);
                                // End of rule, rule complete so add it to rule list of standard style
                                ACLRule newRule = new ACLRule(source, sourceMask, allow);

                                // Add to ACL Rule list
                                aclList.add(newRule);
                            }
                        }
                    }

                    // Increase the count
                    count++;

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
