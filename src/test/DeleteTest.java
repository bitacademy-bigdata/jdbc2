package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {
	public static void main(String[] args) {
		Boolean result = delete(4L);
		System.out.println(result);
	}	
	
	public static Boolean delete(Long no) {
		Boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			//1. JDBC Driver(Mysql) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
	
			//3. Statement 객체 생성
			stmt = conn.createStatement();			
		
			//4. SQL문 실행
			String sql = "delete from emaillist where no = " + no;
					
			int count = stmt.executeUpdate(sql);
			result = (count == 1);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
