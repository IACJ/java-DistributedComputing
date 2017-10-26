package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/xml")
@Path("trains")
@Singleton
public class TrainsResource {
	//�г���Ϣ�б�
	private List<Train> trainList = new ArrayList<Train>();

	public TrainsResource() {
		Train t = new Train("D3052","�Ϻ�����","2013-07-18 10:54","���","2013-07-18 16:50",254,52);
		trainList.add(t);
		Train t1 = new Train("D56","�Ϻ�����","2013-07-18 11:10","����","2013-07-18 13:50",152,30);
		trainList.add(t1);
		Train t2 = new Train("G423","������","2013-07-18 14:05","����","2013-07-18 20:30",856,75);
		trainList.add(t2);
		Train t3 = new Train("G4982","�Ϻ�����","2013-07-18 14:05","����","2013-07-18 15:10",208,15);
		trainList.add(t3);
		Train t4 = new Train("K369","�Ϻ�����","2013-07-19 07:35","�Ϸ�","2013-07-19 14:30",175,28);
		trainList.add(t4);
		Train t5 = new Train("G948","����","2013-07-20 16:40","����","2013-07-20 17:20",108,40);
		trainList.add(t5);
	}
	
	//����startPlaceվ��ʼ���������г���Ϣ
	@GET
	@Path("{startPlace}")
	public List<Train> getStationTrians(
			@PathParam("startPlace") String tStartPlace) {
		List<Train> resultList = new ArrayList<Train>();
		Train temp = new Train();
		for(int i=0; i<trainList.size(); ++i){
			temp = trainList.get(i);
			if(temp.getStartPlace().equals(tStartPlace)){
				resultList.add(temp);
			}
		}
		return resultList;
	}
	
	//����startPlace-->destination���ߵ������г���Ϣ
	@GET
	@Path("{startPlace}/{destination}")
	public List<Train> getLineTrains(
			@PathParam("startPlace") String tStartPlace,
			@PathParam("destination") String tDestination) {
		List<Train> resultList = new ArrayList<Train>();
		Train temp = new Train();
		for(int i=0; i<trainList.size(); ++i){
			temp = trainList.get(i);
			if(temp.getStartPlace().equals(tStartPlace)&&temp.getDestination().equals(tDestination)){
				resultList.add(temp);
			}
		}
		return resultList;
	}

	//����startPlace-->destination������ָ��time�����������г���Ϣ
	@GET
	@Path("{startPlace}/{destination}/{time}")
	public List<Train> getLineTimeTrains(@PathParam("startPlace") String tStartPlace,
			@PathParam("destination") String tDestination,
			@PathParam("time") String tTime) {
		List<Train> resultList = new ArrayList<Train>();
		Train temp = new Train();
		for(int i=0; i<trainList.size(); ++i){
			temp = trainList.get(i);
			if(temp.getStartPlace().equals(tStartPlace)&&temp.getDestination().equals(tDestination)&&
					temp.getStartTime().startsWith(tTime)){
				resultList.add(temp);
			}
		}
		return resultList;
	}
}