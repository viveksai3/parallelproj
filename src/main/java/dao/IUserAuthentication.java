package dao;

import dto.CustomerDetails;

public interface IUserAuthentication {

	int registration(CustomerDetails customer);
	public boolean login(int AccountNumber,String password );


}
