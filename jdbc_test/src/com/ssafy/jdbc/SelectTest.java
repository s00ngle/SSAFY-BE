package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
    private final String DB_ID = "ssafy";
    private final String DB_PWD = "ssafy";
    
    public SelectTest() {
        try {
            Class.forName(DRIVER);
            System.out.println("Driver Loading Complete!!!");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	SelectTest st = new SelectTest();
    	int cnt = st.selectMember("ssafy.com");
    	System.out.println(cnt + "개 선택 성공!!!");
    }
    
    private int selectMember(String edomain) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            StringBuilder sql = new StringBuilder("select * from ssafy_members \n");
            sql.append("where email_domain = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, edomain);

            rs = pstmt.executeQuery();

            System.out.println("email_domain이 ssafy.com인 사람들");
            System.out.println("----------------------------------");
            while (rs.next()) {
                cnt++;
                System.out.println("User ID: " + rs.getString("user_id") + ", User Name : " + rs.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
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
