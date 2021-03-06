package com.acj.myServer;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {

    private InputStream input;
    private String uri;
    private StringBuffer requestStr;
    
    public MyRequest(InputStream input) {
        this.input = input;
    }

    //从InputStream中读取request信息，并从request中获取uri值
    public void parse() {
    	requestStr = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
        	requestStr.append((char) buffer[j]);
        }

        uri = parseUri(requestStr.toString());
        if ("/".equals(uri)){
        	uri= "/index.html";
        }
    }
 
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }

    public String getUri() {
        return uri;
    }

}
