package test;

public class mytest {
	public static void main(String[] args) {
		//创建服务类对象
		MyServiceService ms=new MyServiceService();
		//获取真正的代理服务的对象
		MyServiceDelegate service=ms.getMyServicePort();
		//调用服务SayHi方法，并打印服务返回的字符串
		System.out.println(service.sayHi("张三"));
	}
}
