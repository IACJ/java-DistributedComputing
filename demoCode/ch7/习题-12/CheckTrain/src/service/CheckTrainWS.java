package service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CheckTrainWS {
	private List<Train> trainList = new ArrayList<Train>();//列车时刻表
	
	//初始化列车时刻表
	public CheckTrainWS(){
		Train t = new Train("D3052","上海虹桥","2013/07/18 10:54","武昌","2013/07/18 16:50",254,52);
		trainList.add(t);
		Train t1 = new Train("D56","上海虹桥","2013/07/18 11:10","杭州","2013/07/18 13:50",152,30);
		trainList.add(t1);
		Train t2 = new Train("G423","北京西","2013/07/18 14:05","广州","2013/07/18 20:30",856,75);
		trainList.add(t2);
		Train t3 = new Train("G4982","上海虹桥","2013/07/18 14:05","杭州","2013/07/18 15:10",208,15);
		trainList.add(t3);
	}
	
	//查询服务
	@WebMethod
	public List<Train> checkTrain(String startPlace, String destination,String time){
		List<Train> resultList = new ArrayList<Train>();
		Train temp = new Train();
		for(int i=0; i<trainList.size(); ++i){
			temp = trainList.get(i);
			if(temp.getStartPlace().equals(startPlace)&&temp.getDestination().equals(destination)&&
					temp.getStartTime().startsWith(time)){
				resultList.add(temp);
			}
		}
		return resultList;
	}
}
