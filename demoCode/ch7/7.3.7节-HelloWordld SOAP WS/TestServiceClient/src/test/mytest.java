package test;

public class mytest {
	public static void main(String[] args) {
		//�������������
		MyServiceService ms=new MyServiceService();
		//��ȡ�����Ĵ������Ķ���
		MyServiceDelegate service=ms.getMyServicePort();
		//���÷���SayHi����������ӡ���񷵻ص��ַ���
		System.out.println(service.sayHi("����"));
	}
}
