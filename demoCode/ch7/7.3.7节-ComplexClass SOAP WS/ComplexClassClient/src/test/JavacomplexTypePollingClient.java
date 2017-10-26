package test;

import javax.xml.ws.Response;
import java.util.*;

public class JavacomplexTypePollingClient {
	public static void main(String[] args) {
		JavaComplexTypeService service = new JavaComplexTypeService();
		JavaComplexTypeDelegate port = service.getJavaComplexTypePort();
		Response<GetPersonResponse> getPersonAsync = port.getPersonAsync();
		while(!getPersonAsync.isDone()){
			System.out.println("is not done");
		}
		
		List<Person> rtnList = new ArrayList<Person>();
		try{
			GetPersonResponse getPersonResponse = getPersonAsync.get();
			//�ر�ע��ͻ��˵�Person��Ҫ�����˵�Person����ȫ��ͬ��
			//�ҿͻ��˵�Person���ļ�Ҫ��ͻ�stub JavaComplexTypePollingClient��ͬһ��Ŀ¼��
			rtnList = getPersonResponse.getReturn();
			System.out.println("return size ="+rtnList.size());
			for(Person p:rtnList){
				System.out.println("Person ="+p.getName()+" "+p.getAge()+" "+p.getGrnder());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
