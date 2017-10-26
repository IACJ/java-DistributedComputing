import java.awt.*; 
import java.applet.*;
public class AppletEvent extends Applet
{
	int x, y ;
	Button b ;
	Color clr ;
	/*在该applet构造函数中，代码初始化了变量x，y，clr，建立了一个新的显示“你就按着玩儿吧！”按钮控制，然后把按钮添加到窗体中。*/
	public AppletEvent()
	{	y = 100 ;
		x = 100 ;
		clr = Color.red ;
		b = new Button("你就按着玩儿吧！");
		add("Center", b);
	}
	public void paint(Graphics g) //窗口还包含有用paint方法绘制的字符。
	{
		g.setColor(Color.red);
		g.setFont(new Font("Helvetica", Font.PLAIN, 24));
		g.drawString("InofCD欢迎您!", x, y);
	}
}
