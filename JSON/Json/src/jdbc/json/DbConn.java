package jdbc.json;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConn {
	public static Connection GetConnection() {
		try{
			InitialContext context = new InitialContext();
			DataSource dSource = (DataSource)context.lookup("jdbc/JSON");
			Connection conn = dSource.getConnection();
			return conn;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
