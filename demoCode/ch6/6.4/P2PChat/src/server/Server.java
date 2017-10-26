package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.*;
import common.Node;
import common.UserInfo;

//采用混合P2P结构的中心服务器，用于监听客户
public class Server extends JFrame implements ActionListener {
	JButton sendBtn, cleanBtn, closeBtn, startBtn;//发送、清除、关闭和启动按钮
	TextArea taRecord, taInput;//聊天记录和内容输入文本框
	TextField tfCount;//在线用户总数显示框
	List list;	//显示在线用户的列表
	UserInfo userInfo;	//用户列表对象
	ServerSocket serverSocket;
	Socket socket;
	ObjectOutputStream out;//套接字输出流
	ObjectInputStream in;//套接字输入流
	ArrayList<ObjectOutputStream> allOut;//注释
	InetAddress ip;//ip地址
	int port;//端口号
	boolean isStop;//服务器是否停止
	//用于聊天记录的时间及其格式
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm EEEE");
	Date time = new Date();
	static Server server;//定义服务器
	
	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
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
	
	public static void main(String[] args) {
		server = new Server();
	}
	
	public Server() {
		//组件的初始化
		this.allOut = new ArrayList<ObjectOutputStream>();
		sendBtn = new JButton("发送");
		cleanBtn = new JButton("清除");
		closeBtn = new JButton("关闭服务器");
		startBtn = new JButton("启动服务器");
		closeBtn.setEnabled(false);
		sendBtn.setEnabled(false);
		cleanBtn.setEnabled(false);
		
		taRecord = new TextArea("", 14, 50);
		taRecord.setBackground(Color.lightGray);
		taRecord.setEditable(false);
		taInput=new TextArea("",4,50);
		taInput.setBackground(Color.lightGray);
		tfCount = new TextField();
		tfCount.setBackground(Color.lightGray);
		tfCount.setEditable(false);
		
		//将组件加入Panel容器，并布局
		list = new List();
		
		Panel p1 = new Panel();
		p1.setLayout(new BorderLayout());
		p1.add(new Label("在线用户列表"), BorderLayout.NORTH);
		p1.add(tfCount, BorderLayout.CENTER);
		
		Panel p2 = new Panel();
		p2.setLayout(new BorderLayout());
		p2.add(p1, BorderLayout.NORTH);
		p2.add(list, BorderLayout.CENTER);
		
		Panel p3 = new Panel();
		p3.setLayout(new GridLayout(2, 1, 90, 4));
		p3.add(startBtn);
		p3.add(closeBtn);
		p2.add(p3, BorderLayout.SOUTH);
		
		Panel p4 = new Panel();
		p4.setLayout(new BorderLayout());
		p4.add(new Label("系统记录"), BorderLayout.NORTH);
		p4.add(taRecord, BorderLayout.CENTER);
		
		Panel p5 = new Panel();
		p5.setLayout(new BorderLayout());
		p5.add(p4, BorderLayout.CENTER);
		
		Panel p6 = new Panel();
		p6.setLayout(new BorderLayout(5,9));
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
		p9.setLayout(new BorderLayout());
		p9.add(p2, BorderLayout.EAST);
		p9.add(p8, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(p9, BorderLayout.CENTER);
		setSize(520, 400);
		setTitle("P2PChat服务器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		sendBtn.addActionListener(this);
		cleanBtn.addActionListener(this);
		startBtn.addActionListener(this);
		closeBtn.addActionListener(this);
	}
	
	//监听按钮
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startBtn) {
			startServer();
		}
		else if(e.getSource() == closeBtn) {
			stopServer();
			System.exit(0);
		}
		else if(e.getSource() == sendBtn) {
			if(taInput.getText().equalsIgnoreCase("") ||
					taInput.getText() == null) {
				JOptionPane.showMessageDialog(this, "您尚未输入系统信息！",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
			sendSystemMessage();
		}
		else if(e.getSource() == cleanBtn) {
			taInput.setText("");
		}
	}
	
	//启动服务器
	public void startServer() {
		try{
			serverSocket = new ServerSocket(1234);//监听客户端口为1234
			taRecord.append("等待连接........."+"\n");
			startBtn.setEnabled(false);
			closeBtn.setEnabled(true);
			sendBtn.setEnabled(true);
			cleanBtn.setEnabled(true);
			this.isStop = false;
			userInfo = new UserInfo();
			ServerListenThread serverListenThread = 
				new ServerListenThread(serverSocket, taRecord, tfCount, list, userInfo);
			serverListenThread.start();
		}catch(Exception e) {
			taRecord.append("error0");
		}
	}
	
	//关闭服务器
	public void stopServer() {
		try {
			this.isStop = true;
			serverSocket.close();
			socket.close();
			list.removeAll();
		}catch(Exception e) {
			taRecord.append("close");
		}
	}
	
	//发送系统信息
	public void sendSystemMessage() {
		String message = taInput.getText();
		taRecord.append("系统消息：" + taInput.getText() + "\n");
		taInput.setText("");
		
		try {
			Iterator<ObjectOutputStream> it = this.allOut.iterator();
			while(it.hasNext()) {
				ObjectOutputStream tout = it.next();
				tout.writeObject("系统消息");
				tout.flush();
				tout.writeObject(message);
				tout.flush();
			}
		}catch(Exception e) {
			taRecord.append("error92" + e.toString());
		}
	}
	
	//服务器端监听线程
	public class ServerListenThread extends Thread {
		ServerSocket serverSocket;
		TextArea taRecord;
		List list;
		TextField tfCount;
		UserInfo userInfo;
		Node node;
		ServerReceiveThread serverReceiveThread;
		
		public ServerListenThread(ServerSocket serverSocket,
				TextArea taRecord,TextField tfCount,List list,UserInfo userInfo) {
    		this.serverSocket = serverSocket;
    		this.taRecord = taRecord;
    		this.tfCount = tfCount;
    		this.list = list;
    		this.userInfo = userInfo;
		}
		
		public void run() {
			while(!isStop && !serverSocket.isClosed()) {
				try {
					node = new Node();
					socket = serverSocket.accept();
					InetAddress ip = socket.getInetAddress().getByName(socket.getInetAddress().getHostAddress());
					server.setIp(ip);
					node.setIp(ip);
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					allOut.add(out);
					in = new ObjectInputStream(socket.getInputStream());
					node.setUsername((String)in.readObject());
					node.setPort(in.readInt());
					taRecord.append("恭喜您“" + node.getUsername() + "”连线成功！" + " "+"\n当前客户端名称为：" 
							+ node.getUsername() + "，其IP地址和端口号为：" + ip + ":" + node.getPort() + "\n");
					
					list.add(node.getUsername());
					userInfo.addUser(node);
					taRecord.append("用户“" + node.getUsername() + "”已上线\n");
					String message = "在线用户" + userInfo.getCount() + "人\n";
					tfCount.setText(message);
					
					server.setOut(out);
					
					serverReceiveThread = new ServerReceiveThread(
							socket, taRecord, tfCount, list, node, userInfo,
							in, out, Server.this.allOut, message);
					serverReceiveThread.start();
				}catch(Exception e) {
					taRecord.append("error85" + e.toString());
				}
			}
		}
	}
}
