package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.CustomerDetails;
import utility.Database;

public class UserAuthenticationImpl implements IUserAuthentication {
	Database database=new Database();


	public int registration(CustomerDetails customer)  {
		Connection connection=database.database();
		int accno=0;
		
		try
		{
		String statement="insert into customer_details(first_name,last_name,email_id,password,pancard_number,aadhar_number,address,mobile_number,balance) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=connection.prepareStatement(statement);
		pst.setString(1,customer.getFirstName());
		pst.setString(2,customer.getLastName());
		pst.setString(3,customer.getEmailId());
		pst.setString(4,customer.getPassword());
		pst.setString(5,customer.getPancardNo());
		pst.setString(6,customer.getAadharNumber());
		pst.setString(7, customer.getAddress());
		pst.setString(8,customer.getMobileNumber());
		pst.setLong(9, customer.getBalance());
		int i=pst.executeUpdate();
		if(i==1) {
		String statement1="select * from customer_details where aadhar_number=?";
		PreparedStatement pst1=connection.prepareStatement(statement1);
		pst1.setString(1,customer.getAadharNumber());
		ResultSet resultSet=pst1.executeQuery();
		//
		while(resultSet.next())
		{
			
			accno=resultSet.getInt(1);

			break;
		}
		}
		connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return accno;
	}
	
	public boolean login(int AccountNumber, String password) {
		boolean status=false;
		try
		{
		String statement1="select * from customer_details";
		PreparedStatement pst1=database.database().prepareStatement(statement1);
		ResultSet resultSet=pst1.executeQuery();
		while(resultSet.next())
		{
			//System.out.println(resultSet.getInt(1)+" "+resultSet.getString(5)+AccountNumber+password);
			if(resultSet.getInt(1)==AccountNumber && resultSet.getString(5).contentEquals(password))
			{
				status=true;
				break;
				
			}
			
		}

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}

}
