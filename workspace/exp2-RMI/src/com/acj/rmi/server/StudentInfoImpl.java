package com.acj.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class StudentInfoImpl extends UnicastRemoteObject implements IStudentInfo { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn;   
	
	protected StudentInfoImpl() throws RemoteException, SQLException {
		super();
	    
        try {
    		Class.forName("com.mysql.jdbc.Driver");   

            final String DB_URL = "jdbc:mysql://localhost:3306/rmi_test";
    		final String USER = "root";
    		final String PASS = "";
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
        	System.out.println("Succeeded connecting to the Database!");
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
        } 
		
	}

	@Override
	public List<Student> list() throws RemoteException {
		List<Student> r = new ArrayList<Student>();
		try{
			Statement statement = conn.createStatement();
			String sql = "select * from `students`";
			ResultSet rs;
			rs = statement.executeQuery(sql);
			
		    Long id = null;
		    String name = null;
		    int grade = -1;	    
		    while(rs.next()){
		    	id = rs.getLong("id");
		    	name = rs.getString("name");
		    	grade = rs.getInt("grade");
		    	r.add(new Student(id,name,grade));
		    }
		    rs.close();
		    		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Student getStu(Long id) throws RemoteException {
		Student r = null;
		try{	
			String sql = "select * from `students` WHERE id = ?";			
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs=ps.executeQuery();
			
		    if (rs.next()){
		    	r = new Student();
		    	r.setId(rs.getLong("id") );
		    	r.setName(rs.getString("name"));
		    	r.setGrade( rs.getInt("grade"));
		    }			    
		    rs.close();		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}
