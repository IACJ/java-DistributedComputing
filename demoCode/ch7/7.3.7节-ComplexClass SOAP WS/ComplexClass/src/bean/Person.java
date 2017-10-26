package bean;

import java.io.*;
import java.io.Serializable;

public class Person implements Serializable {
	private String name = "";
	private int age = 0;
	private String grnder ="";
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGrnder() {
		return grnder;
	}
	public void setGrnder(String grnder) {
		this.grnder = grnder;
	}
}
