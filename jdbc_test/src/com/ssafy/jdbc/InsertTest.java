package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {

	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
    private final String DB_ID = "ssafy";
    private final String DB_PWD = "ssafy";

    public InsertTest() {
        try {
            Class.forName(DRIVER);
            System.out.println("Driver Loading Complete!!!");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	InsertTest it = new InsertTest();
    	int cnt = it.registerMember("ssafy2", "1234", "김싸피", "ssafy", "ssafy.com");
    	System.out.println(cnt + "개 입력 성공!!!");
    }
    
    private int registerMember(String id, String pwd, String name, String eid, String edomain) {
    	int cnt = 0;
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
			conn = getConnection();
//			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder("insert into ssafy_members (user_id, user_pwd, user_name, email_id, email_domain) \n");
			sql.append("values (?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, eid);
			pstmt.setString(5, edomain);
			
			cnt = pstmt.executeUpdate();
			
//			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
		} finally {
			try {
				if (pstmt != null) {					
					pstmt.close();
				}
				if (conn != null) {
					conn.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	
    	return cnt;
    }
    
    private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, DB_PWD, DB_ID);
    }
}
