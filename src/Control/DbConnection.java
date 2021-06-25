package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	private String database;
	private String Email;
	private String password;
	
	public DbConnection(String database, String Email, String password) {
		this.database = database;
		this.Email = Email;
		this.password = password;
	}
	
    public Connection Connect() throws Exception {
        
    	Connection con;
    	
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.database, this.Email, this.password);
            return con;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
