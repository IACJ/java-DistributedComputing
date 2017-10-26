package common;

@javax.jws.WebService(targetNamespace = "http://common/", serviceName = "MyServiceService", portName = "MyServicePort")
public class MyServiceDelegate {

	common.MyService myService = new common.MyService();

	public String SayHi(String sname) {
		return myService.SayHi(sname);
	}

}