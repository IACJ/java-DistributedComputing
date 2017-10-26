package service;

public class Train {
	private String trainNum;	//列车 编号
	private String startPlace;	//始发地
	private String startTime;	//始发时间
	private String destination;	//终点
	private String reachTime;	//到达时间
	private float price;	//票价
	private int remainder;	//余票
	
	public Train(){
		trainNum="";
		startPlace="";
		startTime="";
		destination="";
		reachTime="";
		price=0;
		remainder=0;
	}
	
	public Train(String trainNum, String startPlace, String startTime,
			String destination, String reachTime, float price, int remainder) {
		super();
		this.trainNum = trainNum;
		this.startPlace = startPlace;
		this.startTime = startTime;
		this.destination = destination;
		this.reachTime = reachTime;
		this.price = price;
		this.remainder = remainder;
	}
	
	public String getTrainNum() {
		return trainNum;
	}
	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getReachTime() {
		return reachTime;
	}
	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getRemainder() {
		return remainder;
	}
	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}
}
