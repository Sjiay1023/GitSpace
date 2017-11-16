package jdbc.json;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OperateOracle {
	//定义连接所需字符串
	//192.168.0.X位本机地址，1521为端口号，XE为默认数据库名
	private static String USERNAME = "json";
	private static String PASSWORD = "json";
	private static String DRIVER = "oracle.jdbc.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@192.168.46.73:1521:SJY";
	//创建一个数据库连接
	Connection connection = null;
	//创建预编译语句对象
	PreparedStatement pstm = null;
	//创建一个结果集对象
	ResultSet rs = null;
	
//	向数据库增加数据
//	取表中数据总数，加1位新数据id
//	@param id:id编号
//	@param name:名称
//	@param city:城市
	public void AddData(String name,String city){
		connection = getConnection();
		String sql = "select count(*) from TB_CITY where 1=1";
		String sqlStr = "insert into TB_CITY values(?,?,?)";
		int count = 0;
		try{
			//计算表中数据总数
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery(); 
			while(rs.next()){
				count = rs.getInt(1)+1;
				System.out.println(rs.getInt(1));
			}
			pstm = connection.prepareStatement(sqlStr);
			pstm.setInt(1, count);
			pstm.setString(2, name);
			pstm.setString(3, city);
			pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ReleaseResource();
		}
		
	}
	
	//调存储过程向数据库中插入数据
	public void Call_proc(int id,String name,String city){
		connection = getConnection();
		CallableStatement proc = null;
		try{
			proc = connection.prepareCall("{call PKGJSON.USP_S_JSON(?,?,?,?,?)}");
			proc.setInt(1, id);
			proc.setString(2, name);
			proc.setString(3, city);
			proc.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			proc.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
			proc.execute();
			System.out.println(proc.getInt(4));
			System.out.println(proc.getString(5));
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReleaseResource();
		}
		
		
	}
	
	//数据库中删除数据
	//@param name:根据姓名删除
	public void DeleteData(String name){
		connection = getConnection();
		String sqlStr = "delete from TB_CITY where name=?";
		System.out.println(name);
		try{
			//执行删除操作
			pstm = connection.prepareStatement(sqlStr);
			pstm.setString(1, name);
			pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ReleaseResource();
		}
	}
	
	//获取Connection对象
	//@return
	public Connection getConnection(){
		try{
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("成功连接数据库");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("class not find!",e);
		}
		catch (SQLException e) {
			throw new RuntimeException("get connection error!",e);
		}
		return connection;
	}
	
	//释放资源
	public void ReleaseResource(){
		if(rs != null){
			try{
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstm != null){
			try{
				pstm.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null){
			try{
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
