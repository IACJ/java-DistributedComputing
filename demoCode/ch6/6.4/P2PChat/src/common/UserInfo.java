package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

//�û��ڵ��б���
public class UserInfo implements Serializable {
	ArrayList<Node> UserNodeList = null;//�û��ڵ��б�
	
	public UserInfo() {
		UserNodeList = new ArrayList<Node>();
	}
	
	//����û��ڵ�
	public void addUser(Node node) {
		UserNodeList.add(node);
	}
	
	//ɾ���û��ڵ�
	public void deleteUser(Node node) {
		UserNodeList.remove(node);
	}
	
	//ͳ�Ƶ�ǰ�б��нڵ�����
	public int getCount() {
		return UserNodeList.size();
	}
	
	//ͨ���û�������������ָ���ڵ�
	public Node searchUserByName(String username) {
		Iterator<Node> iter = this.UserNodeList.iterator();
		while(iter.hasNext()) {
			Node node = iter.next();
			if(node.username.equals(username))
				return node;
		}
		return null;
	}
	
	//ͨ����������������ָ���ڵ�
	public Node searchUserByIndex(int index) {
		return UserNodeList.get(index);
	}

	public ArrayList<Node> getUserNodeList() {
		return UserNodeList;
	}

	public void setUserNodeList(ArrayList<Node> userNodeList) {
		UserNodeList = userNodeList;
	}
}
