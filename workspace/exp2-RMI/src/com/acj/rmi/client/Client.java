package com.acj.rmi.client;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.acj.rmi.server.IStudentInfo;;

public class Client { 
    public static void main(String args[]){ 
        try { 
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
        	IStudentInfo rStudentInfo =(IStudentInfo) Naming.lookup("rmi://localhost:8888/RStudentInfo"); 
        
            System.out.println("打印学生列表:");
            System.out.println(rStudentInfo.list());
            
            System.out.println("根据id查找学生:");
            com.acj.rmi.server.Student stu = rStudentInfo.getStu((long) 500);
            System.out.println(stu);            
            
        } catch(ConnectException e){
        	e.printStackTrace(); 
        	System.out.println("发生异常：java.rmi.ConnectException ");
        	System.out.println("可能原因：你大概没开服务器？ ");
        }catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();   
        } 
    } 
}
