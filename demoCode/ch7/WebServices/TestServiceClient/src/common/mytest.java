package common;

public class mytest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServiceService ms=new MyServiceService();//�������������
		MyServiceDelegate service=ms.getMyServicePort();//��ȡ�����������Ķ���
		System.out.println(service.sayHi("John"));//���÷����SayHi()��������������÷��صĽ��
	}
}

