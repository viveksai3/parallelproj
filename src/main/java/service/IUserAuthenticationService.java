package service;

import dto.CustomerDetails;

public interface IUserAuthenticationService {
	int registration(CustomerDetails customer);

	boolean login(int accNo, String password);
	public boolean isAadharNo(String aadhar);
	public boolean isMobileNumber(String mobile);
}
