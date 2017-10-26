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

//���������������߳�
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
		sendUserList();//��������δֹͣ����socket����δ�ر�ʱ
		
		while(true) {
			try {
				String request = (String)in.readObject();
				if(request.equalsIgnoreCase("�û�����")) {
					Node client = userInfo.searchUserByName(node.getUsername());
					int j = searchIndex(userInfo, node.getUsername());
					userInfo.deleteUser(client);
					this.allOut.remove(j);
					String msg = "�û�" + node.getUsername() + "����" + "\n";
					//��ȫ�����list��������װ�أ��Ա�֤���·����û��ڵ�index
					list.removeAll();
					int count = userInfo.getCount();
					taRecord.append(msg);
					taRecord.append("�û����ߺ������Ϊ:"+count+"\n");
					
					//����װ��list
					int i=0;
					while(i < count) {
						client = userInfo.searchUserByIndex(i);
						//���������û�
						if(client == null) {
							i++;
							continue;
						}
						list.add(client.getUsername());
						i++;
					}
					
					this.message = "�����û�" + userInfo.getCount() + "��\n";
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
	
	//�����������û�������Ϣ
	public void sendToAll(String msg) {
		try {
			Iterator<ObjectOutputStream> it = this.allOut.iterator();
			while(it.hasNext()) {
				ObjectOutputStream tout = it.next();
				tout.writeObject("������Ϣ");
				tout.flush();
				tout.writeObject(msg);
				tout.flush();
			}
		}catch(Exception e) {
			taRecord.append("error2" + e.toString());
		}
	}
	
	//���������ߵ�P2P�ڵ㷢�������û��б���Ϣ
	public void sendUserList() {
		String userList = "";
		int count = userInfo.getCount();
		int i=0;
		
		//����userInfo�е���Ϣ����list�У�����ʾ
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
		
		//���û��б�userInfo�������ͻ��ˣ��Ը������пͻ��˵�userInfo
		try{
			Iterator<ObjectOutputStream> it = this.allOut.iterator();
			while(it.hasNext()) {
				ObjectOutputStream tout = it.next();
				tout.writeObject("�û��б�");
				tout.flush();
				tout.writeObject(userList);
				tout.flush();
				tout.writeObject(this.message);
				tout.flush();
				
				//��������
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
				taRecord.append("��ǰ�����û��У�");
				for(int r=0; r<userInfo.getCount(); r++)
					taRecord.append(" "+userInfo.searchUserByIndex(r).getUsername());
				taRecord.append(" "+"\n");
			}
		}catch(Exception e) {
			taRecord.append("erroe09"+e.toString()+"\n");
		}
	}
	
	//�����û��ڵ��б����ض��û����Ľڵ�����
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
