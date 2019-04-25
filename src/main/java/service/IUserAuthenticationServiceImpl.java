package service;

import dao.IUserAuthentication;
import dao.UserAuthenticationImpl;
import dto.CustomerDetails;

public class IUserAuthenticationServiceImpl implements IUserAuthenticationService {
	IUserAuthentication object=new UserAuthenticationImpl();
	public boolean isAadharNo(String aadhar)
	{
		boolean status=false;
		if(aadhar.length()==12)
		{
			status =true;
		}
		return status;
		
	}

	public boolean isMobileNumber(String mobile) {
		boolean status=false;
		if(mobile.length()==10)
		{
			status=true;
		}
		return status;
	}

	public int registration(CustomerDetails customer) {
		
		return object.registration(customer);
	}

	public boolean login(int accNo, String password) {
		// TODO Auto-generated method stub
		return object.login(accNo,password);
	}


}
