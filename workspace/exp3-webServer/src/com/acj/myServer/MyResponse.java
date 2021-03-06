package com.acj.myServer;

import java.io.*;

public class MyResponse {

    private static final int BUFFER_SIZE = 1024;
    
    MyRequest request;
    OutputStream output;

    public MyResponse(OutputStream output) {
        this.output = output;
    }

    public void setRequest(MyRequest request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(MyServer.WEB_ROOT, request.getUri());
            if (file.exists()) {	
            	String httpHeader ="HTTP/1.1 200\r\n" + "Content-Type: text/html;charset=utf-8\r\n" + "\r\n";
            	output.write(httpHeader.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
            	responseNotFound();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (fis != null)
                fis.close();
        }
    }
    public void execCGI() throws IOException, InterruptedException{
    	File file = new File(MyServer.WEB_ROOT, request.getUri());
    	if (file.exists()) {
    		Process process = Runtime.getRuntime().exec("python "+MyServer.WEB_ROOT+request.getUri());
    		
        	String httpHeader ="HTTP/1.1 200\r\n" + "Content-Type: text/html;charset=gbk\r\n" + "\r\n" ;
        	 output.write(httpHeader.getBytes());
    		
    		int exitCode = process.waitFor();
    		if (exitCode == 0) {
  
    			readProcessOutput(process.getInputStream(), new PrintStream(output));
    		} else {
    			System.out.println("程序执行出错：" + exitCode);
    		}
    	} else {
    		responseNotFound();
    	}    
    }
    
    private  void readProcessOutput(InputStream inputStream, PrintStream out) throws IOException {
  
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));	
		String line;
		while ((line = reader.readLine()) != null) {
		    out.println(line);
		}
		inputStream.close();
    }
    private void responseNotFound() throws IOException{
        String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n"
                 + "\r\n" + "<h1>404</h1><h1>你访问的资源不存在的哦~~~</h1>";
        output.write(errorMessage.getBytes());
    }
}
