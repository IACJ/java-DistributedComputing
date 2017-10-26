package server;

import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import common.Node;
import common.UserInfo;

//服务器接收请求线程
public class ServerReceiveThread extends Thread {
	TextArea taRecord;
	List list;
	TextField tfCount;
	UserInfo userInfo;
	Node node;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	ArrayList<ObjectOutputStream> allOut;
	boolean isStop;
	String message;
	
	public ServerReceiveThread(Socket socket,TextArea taRecord,TextField tfCount,
				List list,Node node,UserInfo userInfo,ObjectInputStream in,
				ObjectOutputStream out,ArrayList<ObjectOutputStream> allOut,String message) {
		super();
		this.socket = socket;
		this.taRecord = taRecord;
		this.tfCount = tfCount;
		this.list = list;
		this.node = node;
		this.userInfo = userInfo;
		this.in = in;
		this.out = out;
		this.isStop = false;
		this.allOut = allOut;
		this.message = message;
	}
	
	public void run() {
		sendUserList();//当服务器未停止，且socket连接未关闭时
		
		while(true) {
			try {
				String request = (String)in.readObject();
				if(request.equalsIgnoreCase("用户下线")) {
					Node client = userInfo.searchUserByName(node.getUsername());
					int j = searchIndex(userInfo, node.getUsername());
					userInfo.deleteUser(client);
					this.allOut.remove(j);
					String msg = "用户" + node.getUsername() + "下线" + "\n";
					//先全部清空list，再重新装载，以保证重新分配用户节点index
					list.removeAll();
					int count = userInfo.getCount();
					taRecord.append(msg);
					taRecord.append("用户下线后的人数为:"+count+"\n");
					
					//重新装配list
					int i=0;
					while(i < count) {
						client = userInfo.searchUserByIndex(i);
						//跳过下线用户
						if(client == null) {
							i++;
							continue;
						}
						list.add(client.getUsername());
						i++;
					}
					
					this.message = "在线用户" + userInfo.getCount() + "人\n";
					tfCount.setText(this.message);
					sendUserList();
					sendToAll(msg);
					break;
				}
			}catch(Exception e) {
				taRecord.append("error1" + e.toString() + "\n");
			}
		}
	}
	
	//向所有在线用户发送信息
	public void sendToAll(String msg) {
		try {
			Iterator<ObjectOutputStream> it = this.allOut.iterator();
			while(it.hasNext()) {
				ObjectOutputStream tout = it.next();
				tout.writeObject("下线信息");
				tout.flush();
				tout.writeObject(msg);
				tout.flush();
			}
		}catch(Exception e) {
			taRecord.append("error2" + e.toString());
		}
	}
	
	//向所有在线的P2P节点发送在线用户列表信息
	public void sendUserList() {
		String userList = "";
		int count = userInfo.getCount();
		int i=0;
		
		//读出userInfo中的信息放入list中，待显示
		while(i < count) {
			Node client = userInfo.searchUserByIndex(i);
			if(client == null) {
				i++;
				continue;
			}
			userList += client.getUsername();
			userList += "@@";
			i++;
		}
		
		//将用户列表（userInfo）发给客户端，以更新所有客户端的userInfo
		try{
			Iterator<ObjectOutputStream> it = this.allOut.iterator();
			while(it.hasNext()) {
				ObjectOutputStream tout = it.next();
				tout.writeObject("用户列表");
				tout.flush();
				tout.writeObject(userList);
				tout.flush();
				tout.writeObject(this.message);
				tout.flush();
				
				//跳过自身
				if(tout != out) {
					tout.writeObject(userInfo.searchUserByIndex(userInfo.getCount()-1));
					tout.flush();
				}
				else {
					tout.writeObject(userInfo);
					tout.flush();
				}
			}
			
			if(userInfo.getCount() != 0) {
				taRecord.append("当前在线用户有：");
				for(int r=0; r<userInfo.getCount(); r++)
					taRecord.append(" "+userInfo.searchUserByIndex(r).getUsername());
				taRecord.append(" "+"\n");
			}
		}catch(Exception e) {
			taRecord.append("erroe09"+e.toString()+"\n");
		}
	}
	
	//返回用户节点列表中特定用户名的节点索引
	public int searchIndex(UserInfo userInfo, String name) {
		int count = userInfo.getCount();
		int i=0;
		while(i < count) {
			Node client = userInfo.searchUserByIndex(i);
			if(!name.equalsIgnoreCase(client.getUsername()))
				i++;
			else
				return i;
		}
		return i;
	}
}
