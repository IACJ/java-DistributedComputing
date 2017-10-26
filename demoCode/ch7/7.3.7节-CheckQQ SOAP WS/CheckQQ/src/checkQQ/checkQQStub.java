package checkQQ;

public class checkQQStub {
	public static void main(String[] args) {
		QqOnlineWebService port=new QqOnlineWebService();
		QqOnlineWebServiceSoap service=port.getQqOnlineWebServiceSoap();
		String qqNum=new String("283884568");
		String s=service.qqCheckOnline(qqNum);
		//Y = 在线；N = 离线；E = QQ号码错误；
		//A = 商业用户验证失败；V = 免费用户超过数量 
		if(s.equals("Y"))
			s="在线（Y）";
		else if(s.equals("N"))
			s="离线（N）";
		else if(s.equals("A"))
			s="商业用户验证失败（A）";
		else if(s.equals("V"))
			s="免费用户超过数量 （V）";
		else
			s="QQ号码错误 （E）";
		System.out.println(qqNum+"现在状况是："+s);
	}
}
