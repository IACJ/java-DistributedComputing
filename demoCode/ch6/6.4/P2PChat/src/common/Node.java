package common;

import java.net.*;
import java.io.Serializable;

//P2P聊天应用中的节点
public class Node implements Serializable {
	String username = "";	//用户名
	InetAddress ip;	//ip地址
	int port;	//端口号
	Node next = null;	//下一个节点
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public InetAddress getIp() {
		return ip;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "Node [username=" + username + ", ip=" + ip + ", port=" + port
				+ ", next=" + next + "]";
	}
}
