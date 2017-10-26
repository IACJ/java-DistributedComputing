package common;

import java.net.*;
import java.io.Serializable;

//P2P����Ӧ���еĽڵ�
public class Node implements Serializable {
	String username = "";	//�û���
	InetAddress ip;	//ip��ַ
	int port;	//�˿ں�
	Node next = null;	//��һ���ڵ�
	
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
