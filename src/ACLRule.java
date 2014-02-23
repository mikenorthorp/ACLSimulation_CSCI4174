/**
 * This is an ACLRule object, containing if it is deny or allow, the source and destination ip, and protocol,
 * and the port number if applicable
 */
public class ACLRule {
    private IPAddress source;
    private IPAddress dest;
    private boolean allow;
    private int port;
    private String protocol;

    // Different types of constructors for different rules below
    public ACLRule(IPAddress source, IPAddress dest, boolean allow) {
        this.source = source;
        this.dest = dest;
        this.allow = allow;
    }

    public ACLRule(IPAddress source, IPAddress dest, boolean allow, int port) {
        this.source = source;
        this.dest = dest;
        this.allow = allow;
        this.port = port;
    }

    public ACLRule(IPAddress source, IPAddress dest, boolean allow, String protocol) {
        this.source = source;
        this.dest = dest;
        this.allow = allow;
        this.protocol = protocol;
    }

    public ACLRule(IPAddress source, IPAddress dest, boolean allow, int port, String protocol) {
        this.source = source;
        this.dest = dest;
        this.allow = allow;
        this.port = port;
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
}
