import java.awt.*; 
import java.applet.*;
public class AppletEvent extends Applet
{
	int x, y ;
	Button b ;
	Color clr ;
	/*�ڸ�applet���캯���У������ʼ���˱���x��y��clr��������һ���µ���ʾ����Ͱ�������ɣ�����ť���ƣ�Ȼ��Ѱ�ť��ӵ������С�*/
	public AppletEvent()
	{	y = 100 ;
		x = 100 ;
		clr = Color.red ;
		b = new Button("��Ͱ�������ɣ�");
		add("Center", b);
	}
	public void paint(Graphics g) //���ڻ���������paint�������Ƶ��ַ���
	{
		g.setColor(Color.red);
		g.setFont(new Font("Helvetica", Font.PLAIN, 24));
		g.drawString("InofCD��ӭ��!", x, y);
	}
}
