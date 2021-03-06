package com.acj.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

public class Server { 
    public static void main(String args[]) { 

        try { 
            //创建一个远程对象 
            IStudentInfo rStudentInfo = new StudentInfoImpl();
            //本地主机上的远程对象注册表Registry的实例，并指定端口为8888，这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上 
            LocateRegistry.createRegistry(8888); 

            //把远程对象注册到RMI注册服务器上
            Naming.bind("rmi://localhost:8888/RStudentInfo",rStudentInfo); 
            System.out.println(">>>>>INFO:远程RStudentInfo对象绑定成功！"); 
            
        } catch (RemoteException e) { 
            e.printStackTrace(); 
            System.out.println("创建远程对象发生异常！"); 
        } catch (AlreadyBoundException e) {         
            e.printStackTrace(); 
            System.out.println("发生重复绑定对象异常！"); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
            System.out.println("发生URL畸形异常！"); 
        } catch(SQLException e ){
        	e.printStackTrace();
        	System.out.println("发生异常: SQLException"); 
        	System.out.println("可能原因：你没开数据库"); 
        }
    }
}
   
