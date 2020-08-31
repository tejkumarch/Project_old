package com.dxc.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dxc.beans.Student;
import com.dxc.util.ConnectionManager;

public class StudentJdbcDAO implements DAO<Student>{

	Connection con=null;
	PreparedStatement p=null;
	ResultSet rs=null;

	public StudentJdbcDAO() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		super();
		String dri = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/studentsinfo";
		String un = "root";
		String pw = "root";
		
		Class.forName(dri);
		con = DriverManager.getConnection(url, un, pw);// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean save(Student e1) throws SQLException{
				boolean res=false;
		    
				p=con.prepareStatement("insert into students values(?,?,?,?)");

				p.setInt(1, e1.getId());
				p.setString(2, e1.getName());
				p.setString(3, e1.getEmail());
				p.setString(4, e1.getMobile());
				
				if(1==p.executeUpdate())
				{
					res=true;
				}
				return res;
	}

	@Override
	public boolean edit(Student e1) throws SQLException{
		boolean res=false;
		
		String sql="update students set name=?,email=?,mobile=? where studentid=?";
		
		p=con.prepareStatement(sql);
		
		p.setString(1,e1.getName());
		p.setString(2,e1.getEmail());
		p.setString(3,e1.getMobile());
		p.setInt(4, e1.getId());
		if(1==p.executeUpdate())
		{
			res=true;	
		}
		return res;
	}

	@Override
	public boolean delete(int id) throws SQLException, ParseException{
		// TODO Auto-generated method stub
		boolean res=false;
		p=con.prepareStatement("delete from students where studentid=?");
		p.setInt(1, id);
		if(1==p.executeUpdate())
		{
			res=true;
		}
		return res;
	}

	@Override
	public Student find(int id) throws SQLException, ParseException, FileNotFoundException, ClassNotFoundException, IOException{
		// TODO Auto-generated method stub
		Student student=null;
		try {
		con=ConnectionManager.getConnection();
		con.commit();
		p=con.prepareStatement("select * from students where studentid=?");
		
		p.setInt(1,id);
		
		rs=p.executeQuery();
		
		if(rs.next())
		{
			String name=rs.getString(2);
			String email=rs.getString(3);
			String mobile=rs.getString(4);
			
			student =new Student(id, name, email, mobile);
		}
		}
		finally
		{
			con.close();
		}
		return student;
	}

	@Override
	public List<Student> findAll() throws SQLException, ParseException, FileNotFoundException, ClassNotFoundException, IOException{
		
		ArrayList<Student> students=new ArrayList<>();
		try {
		con=ConnectionManager.getConnection();
		con.commit();
		Statement stmt=con.createStatement();
		
		rs=stmt.executeQuery("select * from students");
		
		while(rs.next())
		{
		int id=rs.getInt("studentid");
		String name=rs.getString("name");
		String email=rs.getString(3);
		String mobile=rs.getString(4);
		
		Student student =new Student(id, name, email, mobile);
		students.add(student);
		
	}
		}
		finally {
			con.close();
		}
		return students;
	}

		// TODO Auto-generated method stub
	
}
