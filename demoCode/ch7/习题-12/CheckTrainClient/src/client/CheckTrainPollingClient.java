package client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

public class CheckTrainPollingClient {
	public static void main(String[] args) {
		CheckTrainWSService service = new CheckTrainWSService();
		CheckTrainWSDelegate port = service.getCheckTrainWSPort();
		//调用服务
		Response<CheckTrainResponse> checkTrainAsync = port.checkTrainAsync("上海虹桥", "杭州", "2013/07/18");
		while(!checkTrainAsync.isDone()){
			System.out.println("is not done");
		}
		
		List<Train> rtnList = new ArrayList<Train>();
		try{
			CheckTrainResponse checkTrainResponse = checkTrainAsync.get();
			rtnList = checkTrainResponse.getReturn();
			if(rtnList.size()!=0){
				System.out.println("共有\""+rtnList.size()+"\"趟列车：");
				System.out.println("列车编号\t\t始发地\t\t始发时间\t\t\t终点\t\t到站时间\t\t票价\t\t余票");
				for(Train i:rtnList){
					System.out.println(i.getTrainNum()+"\t\t"+i.getStartPlace()+"\t\t"+i.getStartTime()+
							"\t"+i.getDestination()+"\t"+i.getReachTime()+"\t"+i.getPrice()+"\t\t"+i.getRemainder());
				}
			}else{
				System.out.println("Sorry，没有满足您需求的列车！");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
