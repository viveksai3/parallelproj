package dao;

import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.CustomerDetails;
import dto.Transactions;
import utility.Database;

public class TransactionImpl implements ITransaction {
	CustomerDetails customer=new CustomerDetails();
	Transactions trans=new Transactions();
	Database database=new Database();
	//METHOD FOR DEPOSIT
	public CustomerDetails deposit(int depAmt,int accNo) {
		try {
			String st2="select * from customer_details where account_number=?";
			PreparedStatement pst1=database.database().prepareStatement(st2);
			pst1.setInt(1,accNo);
			ResultSet rs=pst1.executeQuery();
			while(rs.next())
			{
				//CHECKING CONDITION FOR GREATER THAN ZERO
				if(depAmt>0)
				{
					int bal=rs.getInt(10)+depAmt;
					String statement1="update customer_details set balance=? where account_number=?";
					PreparedStatement pst2=database.database().prepareStatement(statement1);
					pst2.setInt(1,bal);
					pst2.setInt(2,accNo);
					pst2.executeUpdate();
					customer.setBalance(bal);
					break;
					}
				else
				{
					System.out.println("amount cannot be zero");
					System.exit(0);

				}

				}
			
			
		}
		catch(Exception e) {System.out.println(e);}
		return customer;

	}
	//METHOD FOR WITHDRAW
	public CustomerDetails withdraw(int withdrawAmt, int accNo) {
		
		try {
			String st2="select * from customer_details where account_number=?";
			PreparedStatement pst1=database.database().prepareStatement(st2);
			pst1.setInt(1,accNo);
			ResultSet rs=pst1.executeQuery();
			while(rs.next())
			{
				//CHECKING THE CONDITION WHERE DEPAMT > BALANCE AVAILABLE
				if(withdrawAmt<rs.getInt(10))
				{
					int bal=rs.getInt(10)-withdrawAmt;
					String statement1="update customer_details set balance=? where account_number=?";
					PreparedStatement pst2=database.database().prepareStatement(statement1);
					pst2.setInt(1,bal);
					pst2.setInt(2,accNo);
					pst2.executeUpdate();
					customer.setBalance(bal);
					break;
					}
				else
				{
					System.out.println("insuffient balance");
					System.exit(0);
				}

				}
			
			
		}
		catch(Exception e) {System.out.println(e);}
		return customer;

	}
	//BALANCE CHECK
	public int balanceCheck(int accNo) {
		
		int bal=0;
		try {
		String st2="select * from customer_details where account_number=?";
		PreparedStatement pst1=database.database().prepareStatement(st2);
		pst1.setInt(1,accNo);
		ResultSet rs=pst1.executeQuery();
		while(rs.next())
		{
			
			bal=rs.getInt(10);
			break;
		}
		}
		catch(Exception e) {System.out.println(e);}
		
		return bal;
	}
	///TRANSFER
	public int transfer(Transactions trans) {
		int transactionId = 0;

		try {
			java.sql.Statement statement=database.database().createStatement();
			ResultSet resultSet=statement.executeQuery("select * from customer_details");
			//VERIFIES THE TO ACCOUNT NUMBER 
			while(resultSet.next()) {
				if(trans.getToAccountNumber()==resultSet.getInt(1)) {
					PreparedStatement preparedStatement=database.database().prepareStatement("select * from customer_details where account_number=?");
					preparedStatement.setLong(1, trans.getFromAccountNumber());
					ResultSet resultSet1=preparedStatement.executeQuery();
					
					double balance = 0;
					while(resultSet1.next()) {
						 balance=resultSet1.getInt(10);
					}
					//checking balance available or not
					if(balance > trans.getAmountTransfer()) {
						
						PreparedStatement preparedStatement1=database.database().prepareStatement("update customer_details set balance=? where account_number=?");
						balance=balance-trans.getAmountTransfer();
						preparedStatement1.setDouble(1, balance);
						preparedStatement1.setLong(2, trans.getFromAccountNumber());
						
						preparedStatement1.executeUpdate();
						
						
						
						PreparedStatement preparedStatement2=database.database().prepareStatement("select * from customer_details where account_number=?");
						preparedStatement2.setLong(1, trans.getToAccountNumber());
						ResultSet resultSet2=preparedStatement2.executeQuery();
						
						while(resultSet2.next()) {
							 balance=resultSet2.getDouble(10);
						}
							
		
						
						PreparedStatement preparedStatement3=database.database().prepareStatement("update customer_details set balance=? where account_number=?");
						balance=balance+trans.getAmountTransfer();
						preparedStatement3.setDouble(1, balance);
						preparedStatement3.setLong(2, trans.getToAccountNumber());
						
						preparedStatement3.executeUpdate();
												
					
						PreparedStatement preparedStatement4=database.database().prepareStatement("insert into transaction_details values(transaction_id.nextval,?,?,?)");
						preparedStatement4.setLong(1,trans.getFromAccountNumber());
						preparedStatement4.setLong(2, trans.getToAccountNumber());
						preparedStatement4.setDouble(3, trans.getAmountTransfer());
					
						preparedStatement4.executeUpdate();
					
										
					
						PreparedStatement preparedStatement5=database.database().prepareStatement("select * from transaction_details where to_account_number=?");
						preparedStatement5.setLong(1, trans.getToAccountNumber());
						ResultSet resultSet3=preparedStatement5.executeQuery();
					
						while(resultSet3.next()) {
							if(resultSet3.getInt(1)>transactionId)
								transactionId=resultSet3.getInt(1);
							
							
						}	
					}
				}
			}
		}
					
					catch(Exception e)
					{
						System.out.println(e);
					}
		return transactionId;
					
					
					}		
}
				
			
	

	
