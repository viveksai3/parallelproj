package utility;

import java.sql.*;
public class Database {
	
	public Connection database() {
		
		Connection connection=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","vivek","viveksai1212");
			if(connection!=null)
			return connection;
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return connection;	
	}
}
