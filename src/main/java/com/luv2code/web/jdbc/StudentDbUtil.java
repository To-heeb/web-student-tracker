package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	  
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection 
			myConn = dataSource.getConnection();
			
			// create a sql statement
			String sql = "SELECT * FROM student ORDER BY last_name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while(myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name"); 
				String email = myRs.getString("email");
				
				// create new student object
				Student tempStudent =  new Student(id, firstName, lastName, email);
				
				// add it to the list of students
				students.add(tempStudent);
				
			}
			
			return students;
		}
		
		finally {
			// close JDBC object
			close(myConn, myStmt, myRs);
			
		}
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		
		try {
			
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
		// get db connection
		myConn = dataSource.getConnection();
		
		// create sql for insert
		String sql = "INSERT INTO student (first_name, last_name, email) values(?, ?, ?)";
		
		myStmt = myConn.prepareStatement(sql);
		// set the param values for the student
		myStmt.setString(1, theStudent.getFirstname());
		myStmt.setString(2, theStudent.getLastname());
		myStmt.setString(3, theStudent.getEmail());
		
		// execute sql insert
		myStmt.execute();

		}
		finally {
			// clean up JDBC object
			close(myConn, myStmt, null);
		}
		
	}

	public Student getStudent(String theStudentId) throws Exception{
		
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			//create sql to get selected student
			String sql = "SELECT * FROM student WHERE id = ?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from the result set row
			if(myRs.next()) {
				String firstname = myRs.getString("first_name");
				String lastname = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// use the studentId during construction
				theStudent = new Student(studentId, firstname, lastname, email);
			}else {
				throw new Exception("Could not find student with id: "+ studentId);
			}
			
			return theStudent;
		}
		finally {
			// clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
	}

	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for update statement
			String sql = "UPDATE student SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
			
			//prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstname());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			//execute SQL statement
			myStmt.execute();
			
		}
		finally {
			
			// close JDBC object
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteStudent(int theStudentId) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for delete statement
			String sql = "DELETE FROM student WHERE id = ?";
			
			//prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, theStudentId);
			
			//execute SQL statement
			myStmt.execute();
			
		}
		finally {
			
			// close JDBC object
			close(myConn, myStmt, null);
		}
		
	}
}
