package dao;

import dto.CustomerDetails;
import dto.Transactions;

public interface ITransaction {

	CustomerDetails deposit(int depAmt,int accNo);

	CustomerDetails withdraw(int withdrawAmt, int accNo);

	int balanceCheck(int accNo);

	int transfer(Transactions trans);

}
