/**
 * This is an ipaddress object
 */
public class IPAddress {
    private int ip1;
    private int ip2;
    private int ip3;
    private int ip4;
    private int port;

    // Basic ipaddress without port
    public IPAddress(int ip1, int ip2, int ip3, int ip4) {
        this.ip1 = ip1;
        this.ip2 = ip2;
        this.ip3 = ip3;
        this.ip4 = ip4;
        this.port = -1;
    }

    // ipaddress with port
    public IPAddress(int ip1, int ip2, int ip3, int ip4, int port) {
        this.ip1 = ip1;
        this.ip2 = ip2;
        this.ip3 = ip3;
        this.ip4 = ip4;
        this.port = port;
    }

    /* Getters and setters */
    public int getIp1() {
        return ip1;
    }

    public void setIp1(int ip1) {
        this.ip1 = ip1;
    }

    public int getIp2() {
        return ip2;
    }

    public void setIp2(int ip2) {
        this.ip2 = ip2;
    }

    public int getIp3() {
        return ip3;
    }

    public void setIp3(int ip3) {
        this.ip3 = ip3;
    }

    public int getIp4() {
        return ip4;
    }

    public void setIp4(int ip4) {
        this.ip4 = ip4;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    // Return the ip address with port if avaialble
    public String toString() {
        if (port == -1) {
            return ip1 + "." + ip2 + "." + ip3 + "." + ip4;
        } else {
            return ip1 + "." + ip2 + "." + ip3 + "." + ip4 + ":" + port;
        }
    }
}
