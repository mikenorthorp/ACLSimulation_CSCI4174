/**
 * This is an ipaddress object
 */
public class IPAddress {
    private int ip1;
    private int ip2;
    private int ip3;
    private int ip4;
    private int port;

    // Basic ipaddress with or without port
    public IPAddress(String ip) {
        // Parse the IP
        int ipIndex = 0;
        int index = 0;
        for (String split: ip.split("\\.", 4)){
            // If it contains : then split on the : to get port number
            if(split.contains(":")) {
                for (String split2: split.split(":")){
                    if(index == 0) {
                        this.ip4 = Integer.parseInt(split2);
                    } else {
                        this.port = Integer.parseInt(split2);
                    }
                    index++;
                }
            } else {
                switch(ipIndex) {
                    case 0:
                        this.ip1 = Integer.parseInt(split);
                        break;
                    case 1:
                        this.ip2 = Integer.parseInt(split);
                        break;
                    case 2:
                        this.ip3 = Integer.parseInt(split);
                        break;
                    case 3:
                        this.ip4 = Integer.parseInt(split);
                        this.port = -1;
                        break;
                    default:
                        break;
                }
            }

            ipIndex++;
        }
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
