package checkQQ;

public class checkQQStub {
	public static void main(String[] args) {
		QqOnlineWebService port=new QqOnlineWebService();
		QqOnlineWebServiceSoap service=port.getQqOnlineWebServiceSoap();
		String qqNum=new String("283884568");
		String s=service.qqCheckOnline(qqNum);
		//Y = ���ߣ�N = ���ߣ�E = QQ�������
		//A = ��ҵ�û���֤ʧ�ܣ�V = ����û��������� 
		if(s.equals("Y"))
			s="���ߣ�Y��";
		else if(s.equals("N"))
			s="���ߣ�N��";
		else if(s.equals("A"))
			s="��ҵ�û���֤ʧ�ܣ�A��";
		else if(s.equals("V"))
			s="����û��������� ��V��";
		else
			s="QQ������� ��E��";
		System.out.println(qqNum+"����״���ǣ�"+s);
	}
}
