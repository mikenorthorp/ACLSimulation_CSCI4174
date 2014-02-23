/**
 * This is an ACL Simulation that reads in an ACL list and a list of packets,
 * then prints out which are allowed and denied.
 */

// Import for file read
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

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

        
        // Read in the packet list file and store in an array list of packets

        // Determine what to deny and allow

    }
}
