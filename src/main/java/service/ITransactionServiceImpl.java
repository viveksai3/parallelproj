package service;

import dao.ITransaction;
import dao.TransactionImpl;
import dto.CustomerDetails;
import dto.Transactions;

public class ITransactionServiceImpl implements ITransactionService {
//object of dao transaction interface 
	ITransaction object=new TransactionImpl();
	public CustomerDetails deposit(int depAmt,int accNo) {
		return object.deposit(depAmt,accNo);
		
	}
	public CustomerDetails withdraw(int withdrawAmt, int accNo) {
		return object.withdraw(withdrawAmt,accNo);
	}
	public int balanceCheck(int accNo) {
		// TODO Auto-generated method stub
		return object.balanceCheck(accNo);
	}
	public int transfer(Transactions trans) {
		// TODO Auto-generated method stub
		return object.transfer(trans);
	}

}
