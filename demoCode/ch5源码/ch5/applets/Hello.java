// The HelloWorld Applet
// When run in a browser or an appletviewer, it displays
// the string "Hello World"


import java.applet.Applet;
import java.awt.*;

public class Hello extends Applet{
	public void init( ){
		setBackground(Color.yellow);
	}

public void paint(Graphics g){
                final int FONT_SIZE = 30;
		Font font = new Font("Serif", Font.BOLD, FONT_SIZE);

	// set font, and color and display message on
	// the screen at position 250,150
		g.setFont(font);
		g.setColor(Color.red);
        // The message in the next line is the one you will see
		g.drawString("Hello,world! good afternoon",150,150);
	}	
} //end class
