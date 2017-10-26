	package service;

	import java.util.*;
	import javax.jws.WebMethod;
	import javax.jws.WebService;
	import bean.Person;

@javax.jws.WebService(
targetNamespace = 
	"http://service/"
,
serviceName = 
	"JavaComplexTypeService"
, 
portName =
	"JavaComplexTypePort"
	,wsdlLocation = "WEB-INF/wsdl/JavaComplexTypeService.wsdl"
)



public class JavaComplexTypeDelegate {

	service.JavaComplexType javaComplexType = new service.JavaComplexType();

			public List<Person> getPerson()  {		
			return javaComplexType.getPerson();
		}
	
}