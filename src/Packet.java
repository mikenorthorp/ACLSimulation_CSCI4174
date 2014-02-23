/**
 * This stores a packet object with two ips (source and destination), protocol, and port number
 */
public class Packet {

    private IPAddress source;
    private IPAddress dest;
    String protocol;

    // Packet without a protocol
    public Packet(IPAddress source, IPAddress dest) {
        this.source = source;
        this.dest = dest;
        this.protocol = "";
    }

    // Packet that contains a protocol
    public Packet(IPAddress source, IPAddress dest, String protocol) {
        this.source = source;
        this.dest = dest;
        this.protocol = protocol;
    }

    /* Getters and setters */

    public IPAddress getSource() {
        return source;
    }

    public void setSource(IPAddress source) {
        this.source = source;
    }

    public IPAddress getDest() {
        return dest;
    }

    public void setDest(IPAddress dest) {
        this.dest = dest;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    // Return the packet with protocol if availible
    public String toString() {
        if (protocol == "") {
            return source + " " + dest;
        } else {
            return source + " " + dest + " " + protocol;
        }
    }
}
