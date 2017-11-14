package com.acj.rmi.server;

public class Student implements java.io.Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; 
    private String name; 
    private int grade; 
 
    public Student() {
		super();
	}
	public Student(Long i, String name, int grade) {
		super();
		this.id = i;
		this.name = name;
		this.grade = grade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", grade=" + grade + "]";
	}

     
}
