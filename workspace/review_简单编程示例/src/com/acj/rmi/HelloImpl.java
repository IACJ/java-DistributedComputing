package com.acj.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements HelloInterface{

	protected HelloImpl() throws RemoteException {
		super();
	}

	public String sayHello(String name) throws RemoteException{
		return "Hello~~ "+name;
	}
}
