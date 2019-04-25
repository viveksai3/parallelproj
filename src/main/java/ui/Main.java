package ui;

import java.util.Scanner;

import dto.CustomerDetails;
import dto.Transactions;
import exceptions.AadharException;
import exceptions.BalanceException;
import exceptions.LoginException;
import exceptions.MobileNumberFormatException;
import exceptions.ToAccountException;
import service.ITransactionService;
import service.ITransactionServiceImpl;
import service.IUserAuthenticationService;
import service.IUserAuthenticationServiceImpl;


public class Main 
{
	static Scanner s;
	static CustomerDetails customerDetails=new CustomerDetails();
	static IUserAuthenticationService customerService=new IUserAuthenticationServiceImpl();
	static ITransactionService transactionService=new ITransactionServiceImpl();
	static Transactions transactionDetails=new Transactions();
	static IUserAuthenticationService customerServiceImpl=new IUserAuthenticationServiceImpl();
	
    public static void main( String[] args )
    {
    	s=new Scanner(System.in);
    	while(true) {
    		//Main Menu display
    	System.out.println("======WELCOME======");
    	System.out.println("Enter your choice : ");
    	System.out.println("1.Register \n 2.Login \n 3.Exit");
    	int ch=s.nextInt();
    	
    	switch(ch) {
    	//Regiter
    	case 1 : System.out.println("Enter First Name: ");
    	customerDetails.setFirstName(s.next());
    	System.out.println("Enter Last Name: ");
    	customerDetails.setLastName(s.next());
    	System.out.println("Enter Email Id:");
    	customerDetails.setEmailId(s.next());
    	System.out.println("Enter password: ");
    	customerDetails.setPassword(s.next());
    	System.out.println("Enter PanCard Number: ");
    	customerDetails.setPancardNo(s.next());
    	System.out.println("Enter Aadhar Card Number: ");
    	customerDetails.setAadharNumber(s.next());
    	
    	//verifies aadhar number from service
    	if(customerServiceImpl.isAadharNo(customerDetails.getAadharNumber())) {
    	System.out.println("Enter Address: ");
    	customerDetails.setAddress(s.next());
    	System.out.println("Enter Mobile No :");
    	customerDetails.setMobileNumber(s.next());
    	
    	//verifies mobile number from service
    	if(customerServiceImpl.isMobileNumber(customerDetails.getMobileNumber())) {
    	customerDetails.setBalance(0);
    	long accountNumber=customerService.registration(customerDetails);
    	//returns account number after registration
    	System.out.println("Successfully Registered ! Your Account Number is "+accountNumber);
    	}else {
    		try {
    			throw new MobileNumberFormatException();
    		}catch(MobileNumberFormatException e) {  			
    		}
    	}
    	}
    	else {
    		try {
    			throw new AadharException();
    		}catch(AadharException e) {  			
    		}
    	}
    		break;
    		//Login
    	case 2 : System.out.println("Enter Account Number: ");
    	int accountNumber1=s.nextInt();
    	System.out.println("Enter password: ");
    	String password=s.next();
    	if(customerService.login(accountNumber1, password)) {
			System.out.println("Login Successfull !!");
			int choice=0;
    		while(choice!=1) {
    			//menu after logging in
    			System.out.println("Enter your choice: ");
    			System.out.println("1.Deposit \n 2.Withdrawal \n 3.Show Balance \n 4.Fund Transfer \n 5.Return to main menu \n 6.Exit");
    			
    			int ch1=s.nextInt();
    			
    			int amount=0;
    			
    			switch(ch1) {
    			//deposit
    			case 1 : System.out.println("Enter the amount: ");
    			amount=s.nextInt();
    			customerDetails=transactionService.deposit( amount,accountNumber1);
    			System.out.println("Your balance is "+customerDetails.getBalance());
    			
    				break;
    				
    				//withdraw
    			case 2 : System.out.println("Enter the amount: ");
    			amount=s.nextInt();
    			customerDetails=transactionService.withdraw(amount,accountNumber1 );
    			if(customerDetails!=null)
    			System.out.println("Your balance is "+customerDetails.getBalance());
    			else {
    				try {
    					throw new BalanceException();
    				}catch(BalanceException e) {
    					
    				}
    			}
    				break;
    			
    				//balance check
    			case 3 : int bal=transactionService.balanceCheck(accountNumber1);
    			System.out.println("Your balance is "+bal);
    				break;
    				
    				//transfer
    			case 4 : System.out.println("Enter the account number to which you want to transfer: ");
    			transactionDetails.setToAccountNumber(s.nextLong());
    			System.out.println("Enter the amount to be transfered: ");
    			transactionDetails.setAmountTransfer(s.nextInt());
    			transactionDetails.setFromAccountNumber(accountNumber1);
    			
    			int i=transactionService.transfer(transactionDetails);
    			
    			if(i==0) {
    				try {
    					throw new ToAccountException();
    				}catch(ToAccountException e) {
    					
    				}
    			}
    			else if(i==1){
    				try {
    					throw new BalanceException();
    				}catch(BalanceException e) {
    					
    				}
    			}
    			else    			
    				System.out.println("Transfered successfully ! Your transaction id is "+i);
    			
    				break;
    				
    			case 5 : choice++;
    				break;
    				
    			case 6 : System.exit(0);
    				
    			default : System.out.println("Wrong Choice !!");
    			}
    		}
    	}
    	else
    		try {
    			throw new LoginException();
    		}catch(LoginException e) {
    			
    		}
    			break;
    		
    		
    	case 3 : System.exit(0);
    	
    	default : System.err.println("Wrong choice !!");
    	}
    	}
    }
}
