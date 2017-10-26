package service;

import java.util.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import bean.Person;	
//该Service没有Input，只有一个Output，该Output为一个List<Person>类型，
//它将返回一个List给客户端，该List中有三条Person结构的数据
@WebService
public class JavaComplexType {
	@WebMethod
	public List<Person> getPerson(){
		List<Person> testList = new ArrayList<Person>();
		Person p = new Person();
		p.setName("Josh");
		p.setAge(23);
		p.setGrnder("male");
		testList.add(p);
		p = new Person();
		p.setName("Taylor");
		p.setAge(33);
		p.setGrnder("female");
		testList.add(p);
		p = new Person();
		p.setName("Tom");
		p.setAge(24);
		p.setGrnder("male");
		testList.add(p);
		
		return testList;
	}
}
