package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
    private final String DB_ID = "ssafy";
    private final String DB_PWD = "ssafy";

    public UpdateTest() {
        try {
            Class.forName(DRIVER);
            System.out.println("Driver Loading Complete!!!");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	UpdateTest ut = new UpdateTest();
    	int cnt = ut.updateMember("ssafy", "5678");
    	System.out.println(cnt + "개 수정 성공!!!");
    }
    
    private int updateMember(String id, String pwd) {
    	int cnt = 0;
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
			conn = getConnection();
//			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder("update ssafy_members \n");
			sql.append("set user_pwd = ? \n");
			sql.append("where user_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			
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
