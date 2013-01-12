package main;
import java.sql.*;

public class DbUtils {
//	private String strServ;
//	private String strPort;
//	private String strBase;
//	private String strUser;
//	private String strPass;
	
	public static Connection Conn;
	public static String strKind[] = {"сумма", "площадь", "прожив"};
	
	public DbUtils(){
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			Conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/quart2012", "om", "1234");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DbUtils(String strServ, String strPort, String strBase,
			String strUser, String strPass) {
		super();
// 		this.strServ = strServ;
//		this.strPort = strPort;
//		this.strBase = strBase;
//		this.strUser = strUser;
//		this.strPass = strPass;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			Conn = DriverManager.getConnection("jdbc:postgresql://" + strServ 
					+ ":" + strPort 
					+ "/" + strBase, 
					strUser, 
					strPass
				);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
