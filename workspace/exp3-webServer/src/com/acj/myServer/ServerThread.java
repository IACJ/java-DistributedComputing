package com.acj.myServer;

import java.net.Socket;

public class ServerThread implements Runnable{
	
    Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	public void run (){
		try{
			// 创建Request对象并解析
	        MyRequest request = new MyRequest(socket.getInputStream());
	        request.parse();
        
	        // 创建 Response 对象
	        MyResponse response = new MyResponse(socket.getOutputStream());
	        response.setRequest(request);
	        
	        print("用户请求: "+request.getUri());
	        
	        // 处理请求并响应
	        if (request.getUri() ==null){
	        	print("丢弃空请求");
	        }else{
	            String a[] = request.getUri().split("\\.");  
	            if (a.length==2 && a[1].equals("py")){
	            	// 执行一段python程序
	            	response.execCGI();
	            	print("已执行CGI并发送结果");
	            }else{
	            	// 发送静态资源：html、ico等
	            	response.sendStaticResource();
	            	print("已发送静态资源");
	            }   
	        }
	        
	        // 关闭 socket 对象
	        socket.close();
	        System.out.println();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
    public static void print(String s){
    	System.out.println("[Server-Thread]: "+s);
    }

}
