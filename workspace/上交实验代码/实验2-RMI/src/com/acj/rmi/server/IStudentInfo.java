package com.acj.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStudentInfo extends Remote{

	 /**
	 * 显示学生列表
	 * @return string
	 * @throws RemoteException
	 */
	public List<Student> list() throws RemoteException; 
	 
	/**
	 * 根据id查找一个学生
	 * @param i
	 * @return String
	 * @throws RemoteException
	 */
	public Student getStu(Long id) throws RemoteException ;

//	public String postStu(Student student) throws RemoteException;
}
