package common;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

//用于获取随机可用的端口号
public class RandomPort {
	public static int getAvaiableRandomPort() {
		Random rand = new Random();
		while(true) {
			try {
				int port = rand.nextInt(65535);
				//测试随机端口是否可用
				ServerSocket socket = new ServerSocket(port);
				socket.close();
				return port;
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
