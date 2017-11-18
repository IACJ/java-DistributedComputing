import java.io.IOException;
import java.nio.charset.Charset;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("hello world!");
		int test = 9;
		
		try {
			byte[] bytes = new byte[10];
			test = System.in.read(bytes);
			System.out.println(test);
			String s = new String(bytes);
			System.out.println(s);
	        String defaultCharsetName=Charset.defaultCharset().displayName();   
	        System.out.println("defaultCharsetName:"+defaultCharsetName); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
