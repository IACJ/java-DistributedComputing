package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import common.Node;
import common.RandomPort;
import common.UserInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.net.*;

//�ͻ��ڵ�
public class Client extends JFrame implements ActionListener {
	static final long serialVersionUID = 42L;
	JButton sendBtn, cleanBtn, loginBtn, logoutBtn;
	TextArea taRecord, taInput;
	TextField tfCount, tfUserName;//�����û������ı��򡢵�¼�û����ı���
	List list;
	ObjectOutputStream out;
	ObjectInputStream in;
	UserInfo userInfo;
	Socket socket;
	Node node;
	int selectedPort;	//�ͻ��˷����������ö˿�
	int clientListenPort;//�ͻ��˼����˿�
	InetAddress ip;
	int port;
	SocketAddress socketAddress;
	ClientReceiveThread clientReceiveThread;
	String userName;
	static Client client;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm EEEE");
	Date time = new Date();
	
	public static void main(String[] args) {
		client = new Client();
	}
	
	public Client() {
		sendBtn = new JButton("����");
		sendBtn.setMnemonic('S');
		cleanBtn = new JButton("���");
		logoutBtn = new JButton("�ر�");
		loginBtn = new JButton("��¼");
		logoutBtn.setEnabled(false);
		sendBtn.setEnabled(false);
		cleanBtn.setEnabled(false);
		
		taRecord = new TextArea("", 14, 50);
		taRecord.setBackground(Color.lightGray);
		taInput = new TextArea("", 4, 50);
		taInput.setBackground(Color.lightGray);
		tfCount = new TextField();
		tfCount.setBackground(Color.lightGray);
		tfUserName = new TextField();
		tfUserName.setBackground(Color.lightGray);
		taRecord.setEditable(false);
		tfCount.setEditable(false);
		
		list = new List();
		list.add("all");
		
		Panel p1 = new Panel();
		p1.setLayout(new BorderLayout());
		p1.add(new Label("�����б�"), BorderLayout.NORTH);
		p1.add(tfCount, BorderLayout.CENTER);
		
		Panel p3=new Panel();
		p3.setLayout(new GridLayout(4,1));
		p3.add(new Label("�û�����"));
		p3.add(tfUserName);
		p3.add(loginBtn);
		p3.add(logoutBtn);
		
		Panel p2 = new Panel();
		p2.setLayout(new BorderLayout());
		p2.add(p1, BorderLayout.NORTH);
		p2.add(p3, BorderLayout.SOUTH);
		p2.add(list, BorderLayout.CENTER);
		
		Panel p4 = new Panel();
		p4.setLayout(new BorderLayout());
		p4.add(new Label("�����¼"), BorderLayout.NORTH);
		p4.add(taRecord, BorderLayout.CENTER);
		
		Panel p5 = new Panel();
		p5.setLayout(new BorderLayout());
		p5.add(p4, BorderLayout.CENTER);
		
		Panel p6 = new Panel();
		p6.setLayout(new BorderLayout(5, 9));
		p6.add(p5, BorderLayout.CENTER);
		p6.add(taInput, BorderLayout.SOUTH);
		
		Panel p7 = new Panel();
		p7.setLayout(new GridLayout(1, 2, 50, 50));
		p7.add(sendBtn);
		p7.add(cleanBtn);
		
		Panel p8 = new Panel();
		p8.setLayout(new BorderLayout());
		p8.add(p6, BorderLayout.CENTER);
		p8.add(p7, BorderLayout.SOUTH);
		
		Panel p9 = new Panel();
		p9.setLayout(new BorderLayout(5, 5));
		p9.add(p2, BorderLayout.EAST);
		p9.add(p8, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(p9, BorderLayout.CENTER);
		
		setSize(600, 500);
		setTitle("P2PChat�ͻ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		sendBtn.addActionListener(this);
		cleanBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		list.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginBtn)
			Login();
		else if(e.getSource() == logoutBtn) {
			Logout();
			System.exit(0);
		}else if(e.getSource() == sendBtn) {
			if(taInput.getText().equalsIgnoreCase("") || taInput.getText() == null)
				JOptionPane.showMessageDialog(this, "����δ����������Ϣ��", "Information", JOptionPane.INFORMATION_MESSAGE);
			sendMessage();
		}else if(e.getSource() == cleanBtn)
			taInput.setText("");
	}
	
	public void Login() {
		new Thread(new ComWithServer()).start();
	}
	
	public void Logout() {
		logoutBtn.setEnabled(false);
		sendBtn.setEnabled(false);
		cleanBtn.setEnabled(false);
		loginBtn.setEnabled(true);
		
		if(socket.isClosed())
			return;
		try {
			ObjectOutputStream out = client.getOut();
			out.writeObject("�û�����");
			out.flush();
			in.close();
			out.close();
		}catch(Exception e) {
			taRecord.append("error92" + e.toString());
		}
	}
	
	public void sendMessage() {
		try {
			if(list.getSelectedIndex() == 0) {//Ⱥ��
				String name = tfUserName.getText();
				String msg = taInput.getText();
				for(int j=0; j<userInfo.getCount(); j++) {
					InetAddress ip1 = userInfo.searchUserByIndex(j).getIp();
					int port1 = userInfo.searchUserByIndex(j).getPort();
					new ClientSendThread(ip1, port1, msg, name).start();
				}
				taInput.setText("");
			}else {	//��ָ���û�������Ϣ
				String toSomebody = list.getSelectedItem().toString();
				taRecord.append(tfUserName.getText() + "��" + toSomebody + "˵��" + "\n");
				taRecord.append(" " + taInput.getText() + "\n");
				String name = tfUserName.getText();
				String msg = taInput.getText();
				InetAddress ip = userInfo.searchUserByName(toSomebody).getIp();
				int port = userInfo.searchUserByName(toSomebody).getPort();
				client.setSelectedPort(port);
				String ip1 = ip.getHostAddress();
				
				Socket clientSocket = new Socket(ip1, port);
				
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				
				out.writeObject("������Ϣ");
				out.flush();
				out.writeObject(name);
				out.flush();
				out.writeObject(msg);
				out.flush();
				
				//////
				String shut1;
				while(true) {
					shut1 = (String)in.readObject();
					if(shut1.equalsIgnoreCase("A����ر����ӣ�")) {
						out.writeObject("B�յ�A����ر����ӣ�");
						out.flush();
						break;
					}
				}
				
				String shut3;
				while(true) {
					shut3 = (String)in.readObject();
					if(shut3.equalsIgnoreCase("A�յ�B�Ĺر�ȷ�ϣ�"))
						break;
				}
				out.close();
				clientSocket.close();
				//////
				
				taInput.setText("");
			}
		}catch(Exception e) {
			taRecord.append("�����������û�����ʱ���ִ���" + e.toString());
		}
	}
	
	public class ComWithServer implements Runnable {
		public void run() {
			try {
				node  = new Node();
				socket = new Socket("127.0.0.1", 1234);
				ip = socket.getLocalAddress();
				client.setIp(ip);
				client.setPort(Client.this.clientListenPort);
				taRecord.append("��ϲ������" + tfUserName.getText() + "�����Ѿ����߳ɹ��� ����IP��ַΪ��" + ip + "\n");
				clientListenPort = RandomPort.getAvaiableRandomPort();
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(tfUserName.getText());
				out.flush();
				out.writeInt(Client.this.clientListenPort);
				out.flush();
				client.setOut(out);
				client.setUserName(tfUserName.getText());
				in = new ObjectInputStream(socket.getInputStream());
				
				int selectedPort = client.getSelectedPort();
				clientReceiveThread = new ClientReceiveThread(node, socket, in, out, list, taRecord,
						taInput, tfCount, ip, Client.this.clientListenPort, selectedPort);
				clientReceiveThread.start();
				loginBtn.setEnabled(false);
				logoutBtn.setEnabled(true);
				sendBtn.setEnabled(true);
				cleanBtn.setEnabled(true);
				
				while(true) {
					try {
						String type = (String)in.readObject();
						if(type.equalsIgnoreCase("�û��б�")) {
							String userList = (String)in.readObject();
							String userName[] = userList.split("@@");
							list.removeAll();
							
							int i = 0;
							list.add("all");
							while(i < userName.length) {
								list.add(userName[i]);
								i++;
							}
							String msg = (String)in.readObject();
							tfCount.setText(msg);
							
							//��ȡ�����û��б�
							Object o = in.readObject();
							if(o instanceof UserInfo)
								userInfo = (UserInfo)o;
							else
								userInfo.addUser((Node)o);
						}else if(type.equalsIgnoreCase("ϵͳ��Ϣ")) {
							String b = (String)in.readObject();
							taRecord.append("ϵͳ��Ϣ��" + b + "\n");
						}else if(type.equalsIgnoreCase("������Ϣ")) {
							String msg = (String)in.readObject();
							taRecord.append("�û�������Ϣ��" + msg + "\n");
						}
					}catch(Exception e) {
						taRecord.append("error6" + e.toString());
					}
				}
			}catch(Exception e) {
				taRecord.append("error12" + e.toString());
			}
		}
	}
	
    public void setIp(InetAddress ip) {
    	this.ip = ip;
    }
    
    public InetAddress getIp() {
    	return ip;
    }
    
    public void setPort(int port) {
    	this.port = port;
    }
    
    public int getPort() {
    	return port;
    }
    
    public void setclientinfo(UserInfo userInfo) {
    	this.userInfo = userInfo;
    }
    
    public UserInfo getUserInfo() {
    	return this.userInfo;
    }
    
    public void setSelectedPort(int port) {
    	this.selectedPort = port;
    }
    
    public int getSelectedPort() {
    	return selectedPort;
    }
	
	public void setUserName(String userName) {
		this.userName=tfUserName.getText();
	}
	
	public String getUserName() {
		return userName;
		
	}
	
	public void setOut(ObjectOutputStream out) {
		this.out=out;
	}
	
	public ObjectOutputStream getOut() {
		return out;
	}
}
