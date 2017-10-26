package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

//用户节点列表类
public class UserInfo implements Serializable {
	ArrayList<Node> UserNodeList = null;//用户节点列表
	
	public UserInfo() {
		UserNodeList = new ArrayList<Node>();
	}
	
	//添加用户节点
	public void addUser(Node node) {
		UserNodeList.add(node);
	}
	
	//删除用户节点
	public void deleteUser(Node node) {
		UserNodeList.remove(node);
	}
	
	//统计当前列表中节点总数
	public int getCount() {
		return UserNodeList.size();
	}
	
	//通过用户名检索并返回指定节点
	public Node searchUserByName(String username) {
		Iterator<Node> iter = this.UserNodeList.iterator();
		while(iter.hasNext()) {
			Node node = iter.next();
			if(node.username.equals(username))
				return node;
		}
		return null;
	}
	
	//通过索引检索并返回指定节点
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
