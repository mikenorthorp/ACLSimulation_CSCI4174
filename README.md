README
======

README and code base best viewed on [Github](https://github.com/mikenorthorp/ACLSimulation_CSCI4174)


This is an assignment for my Server Side Scripting class CSCI4174 Assignment 2

It is a simulation of an Access Control List or ACL for packet filtering. It is just a general mockup and does not entirely match the professional specifications of an actual ACL, but made for this assignment. 

Requirements
------------

This program requires `java` to be installed on the computer you are running this on.

Installation
------------

1. Copy files found in /src/ folder to your favorite IDE and compile, or compile using terminal.

Making the Program Do Things
----------------------------

1. Sample ACL lists and Packet lists are included that you can run. You can view the various sample files to see the format to put ACL and packets in. This program supports both extended and standard ACLs.
2. Type in the file name for both the ACL and packet list at runtime.
3. The program will print out which packets were denied and which were allowed based on the ACL.
4. All packets are denied by default unless specified in your ACL.

Example ACL
-----------

	- access-list acl-number {deny/allow} sourceIP sourceMask
	- access-list acl-number {deny/allow} protocol sourceIP sourceMask DestIp destMask [eq portnum]

	- ex. access-list 1 deny 172.168.4.3 0.0.0.255
	- ex. access-list 1 deny tcp 172.168.4.3 0.0.0.255 173.3.4.0 0.0.0.255 eq 20

Example Packets
---------------

	- sourceIP[:port] destIP[:port] [protocol]
	- ex. 192.168.34.4 172.34.243.22
	- ex. 192.168.34.4:80 172.34.243.22:80 ip





