package service;

import java.util.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import bean.Person;	
//��Serviceû��Input��ֻ��һ��Output����OutputΪһ��List<Person>���ͣ�
//��������һ��List���ͻ��ˣ���List��������Person�ṹ������
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
