package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.Transactions;
import junit.framework.Assert;

class TransactionImplTest {
	TransactionImpl object=new TransactionImpl();
	Transactions obj1=new Transactions();
	@Test
	void testDeposit() {
		Assert.assertEquals(11000,object.deposit(2500,30).getBalance());

	}

	@Test
	void testWithdraw() {
		Assert.assertEquals(8500,object.withdraw(500,30).getBalance());
	}

	@Test
	void testBalanceCheck() {
		Assert.assertEquals(9000,object.balanceCheck(30));
	}

	@Test
	void testTransfer() {
		obj1.setAmountTransfer(500);
		obj1.setFromAccountNumber(30);
		obj1.setToAccountNumber(29);
		Assert.assertEquals(129,object.transfer(obj1));

		
	}

}
