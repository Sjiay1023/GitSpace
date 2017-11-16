package jdbc.json;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OperateOracle {
	//�������������ַ���
	//192.168.0.Xλ������ַ��1521Ϊ�˿ںţ�XEΪĬ�����ݿ���
	private static String USERNAME = "json";
	private static String PASSWORD = "json";
	private static String DRIVER = "oracle.jdbc.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@192.168.46.73:1521:SJY";
	//����һ�����ݿ�����
	Connection connection = null;
	//����Ԥ����������
	PreparedStatement pstm = null;
	//����һ�����������
	ResultSet rs = null;
	
//	�����ݿ���������
//	ȡ����������������1λ������id
//	@param id:id���
//	@param name:����
//	@param city:����
	public void AddData(String name,String city){
		connection = getConnection();
		String sql = "select count(*) from TB_CITY where 1=1";
		String sqlStr = "insert into TB_CITY values(?,?,?)";
		int count = 0;
		try{
			//���������������
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
	
	//���洢���������ݿ��в�������
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
	
	//���ݿ���ɾ������
	//@param name:��������ɾ��
	public void DeleteData(String name){
		connection = getConnection();
		String sqlStr = "delete from TB_CITY where name=?";
		System.out.println(name);
		try{
			//ִ��ɾ������
			pstm = connection.prepareStatement(sqlStr);
			pstm.setString(1, name);
			pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ReleaseResource();
		}
	}
	
	//��ȡConnection����
	//@return
	public Connection getConnection(){
		try{
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("�ɹ��������ݿ�");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("class not find!",e);
		}
		catch (SQLException e) {
			throw new RuntimeException("get connection error!",e);
		}
		return connection;
	}
	
	//�ͷ���Դ
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
