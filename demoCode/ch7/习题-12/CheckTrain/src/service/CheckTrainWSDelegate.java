package service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@javax.jws.WebService(targetNamespace = "http://service/", serviceName = "CheckTrainWSService", portName = "CheckTrainWSPort", wsdlLocation = "WEB-INF/wsdl/CheckTrainWSService.wsdl")
public class CheckTrainWSDelegate {

	service.CheckTrainWS checkTrainWS = new service.CheckTrainWS();

	public List<Train> checkTrain(String startPlace, String destination,
			String time) {
		return checkTrainWS.checkTrain(startPlace, destination, time);
	}

}