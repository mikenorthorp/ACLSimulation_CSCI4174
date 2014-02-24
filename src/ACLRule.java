/**
 * This is an ACLRule object, containing if it is deny or allow, the source and destination ip, and protocol,
 * and the port number if applicable
 */
public class ACLRule {
    private IPAddress source;
    private IPAddress sourceMask;
    private IPAddress dest;
    private IPAddress destMask;
    private boolean allow;
    private int port;
    private String protocol;

    // Different types of constructors for different rules below
    public ACLRule(IPAddress source, IPAddress sourceMask, boolean allow) {
        this.source = source;
        this.sourceMask = sourceMask;
        this.allow = allow;

        // Set others to null
        this.dest = null;
        this.destMask = null;
        this.port = -1;
        this.protocol = null;
    }

    public ACLRule(IPAddress source, IPAddress sourceMask, IPAddress dest, IPAddress destMask, boolean allow, String protocol, int port) {
        this.source = source;
        this.sourceMask = sourceMask;
        this.dest = dest;
        this.destMask = destMask;
        this.allow = allow;
        this.port = port;
        this.protocol = protocol;
    }

    public ACLRule(IPAddress source, IPAddress sourceMask, IPAddress dest, IPAddress destMask, boolean allow, String protocol) {
        this.source = source;
        this.sourceMask = sourceMask;
        this.dest = dest;
        this.destMask = destMask;
        this.allow = allow;
        this.protocol = protocol;

        // Set others to null
        this.port = -1;
    }


    /* Getters and setters */

    public IPAddress getSourceMask() {
        return sourceMask;
    }

    public void setSourceMask(IPAddress sourceMask) {
        this.sourceMask = sourceMask;
    }

    public IPAddress getDestMask() {
        return destMask;
    }

    public void setDestMask(IPAddress destMask) {
        this.destMask = destMask;
    }

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

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    // Return the full rule if avaialble
    public String toString() {
        return "access-list" + allow + " " + protocol + " " + source + " " + sourceMask + " " + dest + " " + destMask
                + " " + port;
    }
}
