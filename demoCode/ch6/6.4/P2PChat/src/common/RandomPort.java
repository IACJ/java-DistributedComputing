package common;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

//���ڻ�ȡ������õĶ˿ں�
public class RandomPort {
	public static int getAvaiableRandomPort() {
		Random rand = new Random();
		while(true) {
			try {
				int port = rand.nextInt(65535);
				//��������˿��Ƿ����
				ServerSocket socket = new ServerSocket(port);
				socket.close();
				return port;
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
