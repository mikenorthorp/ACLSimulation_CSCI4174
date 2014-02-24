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

                // Add extended rule without port
                if (count == 8) {
                    // End of rule, rule complete so add it to rule list of standard style
                    ACLRule newRule = new ACLRule(source, sourceMask, dest, destMask, allow, protocol);

                    // Add to ACL Rule list
                    aclList.add(newRule);

                    // Add extended rule with port
                } else if (count == 10) {
                    // End of rule, rule complete so add it to rule list of standard style
                    ACLRule newRule = new ACLRule(source, sourceMask, dest, destMask, allow, protocol, port);

                    // Add to ACL Rule list
                    aclList.add(newRule);
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
                // Create a tokenizer for this line
                StringTokenizer tokenizer = new StringTokenizer(tempLine);
                String current = "";
                int count = 0;

                // Set up variables for packet to be put into packet list
                IPAddress source = null;
                IPAddress dest = null;
                String protocol = null;

                // Loop through tokens in line to get rule stuff
                while (tokenizer.hasMoreTokens()) {
                    current = tokenizer.nextToken();

                    // Source ip of packet
                    if (count == 0) {
                        source = new IPAddress(current);
                        // Dest ip of packet
                    } else if (count == 1) {
                        dest = new IPAddress(current);
                        // Protocol of packet
                    } else if (count == 2) {
                        protocol = current;
                    }

                    // Increase the count
                    count++;
                }

                // If count is at 2 then no protocol, else 3 then protocol for packet
                if (count == 2) {
                    // Create new packet
                    Packet newPacket = new Packet(source, dest);

                    // Add to Packet list
                    packetList.add(newPacket);

                } else if (count == 3) {
                    // Create new packet
                    Packet newPacket = new Packet(source, dest, protocol);

                    // Add to Packet list
                    packetList.add(newPacket);
                }
            }
            // Close the data input stream
            dataIn.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        // Determine what to deny and allow by going through the rule list for each packet, then printing out a
        // deny or allow

        // Loop through the packet list and check agaisnt the entire ACLrule list until it finds a rule that matches
        // Then print it out and the deny or allow for the packet
        for (Packet packet : packetList) {
            // Loop through the acl rule list and print it out
            for (ACLRule rule : aclList) {
                boolean isExtended = false;
                boolean allow = false;
                boolean isMatch = true;
                boolean isMatchSource = true;
                boolean isMatchDest = true;
                boolean isMatchPort = true;

                // Check if packet printed or not
                if (!packet.isPrinted()) {
                    // Check if extended or standard ACL
                    if (rule.getProtocol() != null) {
                        isExtended = true;
                    }

                    // Check if deny or allow rule
                    if (rule.isAllow() == true) {
                        allow = true;
                    }

                    // Extended logic
                    if (isExtended) {

                        // Check source ip if they match (rule and packet)
                        if (!(rule.getSource().getIp1() == packet.getSource().getIp1() ||
                                rule.getSourceMask().getIp1() == 255)) {
                            isMatchSource = false;
                        }

                        if (!(rule.getSource().getIp2() == packet.getSource().getIp2() ||
                                rule.getSourceMask().getIp2() == 255)) {
                            isMatchSource = false;
                        }

                        if (!(rule.getSource().getIp3() == packet.getSource().getIp3() ||
                                rule.getSourceMask().getIp3() == 255)) {
                            isMatchSource = false;
                        }

                        if (!(rule.getSource().getIp4() == packet.getSource().getIp4() ||
                                rule.getSourceMask().getIp4() == 255)) {
                            isMatchSource = false;
                        }

                        // Check dest ip if they match (rule and packet)
                        if (!(rule.getSource().getIp1() == packet.getSource().getIp1() ||
                                rule.getSourceMask().getIp1() == 255)) {
                            isMatchDest = false;
                        }

                        if (!(rule.getSource().getIp2() == packet.getSource().getIp2() ||
                                rule.getSourceMask().getIp2() == 255)) {
                            isMatchDest = false;
                        }

                        if (!(rule.getSource().getIp3() == packet.getSource().getIp3() ||
                                rule.getSourceMask().getIp3() == 255)) {
                            isMatchDest = false;
                        }

                        if (!(rule.getSource().getIp4() == packet.getSource().getIp4() ||
                                rule.getSourceMask().getIp4() == 255)) {
                            isMatchDest = false;
                        }

                        // Check if port numbers do not match if one exists for packet
                        // Else check if a port exists for the rule, in which case no
                        // rules match here
                        if(packet.getSource().getPort() == -1 && rule.getPort() != -1) {
                            // No match
                            isMatchPort = false;
                        }
                        // Check if port of rule = port of source packet
                        // If not then no match
                        else if (!(rule.getPort() == packet.getSource().getPort())) {
                            isMatchPort = false;
                        }

                        // Check if all matches are true
                        if (isMatchDest && isMatchSource && isMatchPort) {
                            // Print out packet name
                            System.out.print("\n" + packet);
                            if(allow) {
                                System.out.println(" permited");
                            } else {
                                System.out.println(" denied");
                            }

                            // Set packet as printed
                            packet.setPrinted(true);
                        }

                    } else {
                        // Standard logic
                        // Only look at source IP
                        // Check if source in rule matches source IP in packet
                        // Take mask into account when checking

                        if (!(rule.getSource().getIp1() == packet.getSource().getIp1() ||
                            rule.getSourceMask().getIp1() == 255)) {
                            isMatch = false;
                        }

                        if (!(rule.getSource().getIp2() == packet.getSource().getIp2() ||
                                rule.getSourceMask().getIp2() == 255)) {
                            isMatch = false;
                        }

                        if (!(rule.getSource().getIp3() == packet.getSource().getIp3() ||
                                rule.getSourceMask().getIp3() == 255)) {
                            isMatch = false;
                        }

                        if (!(rule.getSource().getIp4() == packet.getSource().getIp4() ||
                                rule.getSourceMask().getIp4() == 255)) {
                            isMatch = false;
                        }

                        // Check if isMatch is true and print out packet name and allow or deny
                        if (isMatch) {
                            // Print out packet name
                            System.out.print("\n" + packet);
                            if(allow) {
                                System.out.println(" permited");
                            } else {
                                System.out.println(" denied");
                            }

                            // Set packet as printed
                            packet.setPrinted(true);
                        }

                        // Do nothing if packet is not matched, check later
                    }
                }
            }
        }

        // If any packets did not match a rule, automatically deny them
        for (Packet packet : packetList) {
            // Print out packet name
            if (!packet.isPrinted()) {
                System.out.println("\n" + packet + " denied");
            }
        }
    }
}
