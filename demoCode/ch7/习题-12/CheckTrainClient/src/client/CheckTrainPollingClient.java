package client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

public class CheckTrainPollingClient {
	public static void main(String[] args) {
		CheckTrainWSService service = new CheckTrainWSService();
		CheckTrainWSDelegate port = service.getCheckTrainWSPort();
		//���÷���
		Response<CheckTrainResponse> checkTrainAsync = port.checkTrainAsync("�Ϻ�����", "����", "2013/07/18");
		while(!checkTrainAsync.isDone()){
			System.out.println("is not done");
		}
		
		List<Train> rtnList = new ArrayList<Train>();
		try{
			CheckTrainResponse checkTrainResponse = checkTrainAsync.get();
			rtnList = checkTrainResponse.getReturn();
			if(rtnList.size()!=0){
				System.out.println("����\""+rtnList.size()+"\"���г���");
				System.out.println("�г����\t\tʼ����\t\tʼ��ʱ��\t\t\t�յ�\t\t��վʱ��\t\tƱ��\t\t��Ʊ");
				for(Train i:rtnList){
					System.out.println(i.getTrainNum()+"\t\t"+i.getStartPlace()+"\t\t"+i.getStartTime()+
							"\t"+i.getDestination()+"\t"+i.getReachTime()+"\t"+i.getPrice()+"\t\t"+i.getRemainder());
				}
			}else{
				System.out.println("Sorry��û��������������г���");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
