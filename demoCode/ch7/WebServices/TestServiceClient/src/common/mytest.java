package common;

public class mytest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServiceService ms=new MyServiceService();//创建服务类对象
		MyServiceDelegate service=ms.getMyServicePort();//获取真正代理服务的对象
		System.out.println(service.sayHi("John"));//调用服务的SayHi()方法，并输出调用返回的结果
	}
}

