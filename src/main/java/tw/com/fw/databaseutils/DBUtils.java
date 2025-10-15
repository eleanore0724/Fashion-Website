package tw.com.fw.databaseutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	 // JDBC Driver
	 private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	 // JDBC 連接路徑(本機)
	 private String JDBC_URL = 
	"jdbc:mysql://127.0.0.1:3306/fw";
	 // 資料庫的帳號
	private String JDBC_USERNAME = "root";
	 // 資料庫密碼
	private String JDBC_PASSWORD = "123456";
	 // JDBC 連接類
	private Connection conn;
	
	// 建構子
	 public DBUtils() {
         try {
             Class.forName(JDBC_DRIVER);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
     }
	 
	 // 靜態方法
     public static DBUtils getDataBase() {
         return new DBUtils();
     }
     // 調用資料庫連接類
     public Connection getConnection() {
         try {
             conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return conn;
     }
     
     /*
      * close方法多型
      */
     
     //關閉Connection
     public void close(Connection conn) {
         if(conn != null) {
             try {
                 conn.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
     //關閉PreparedStatement
     public void close(PreparedStatement ps) {
         if(ps != null) {
             try {
                 ps.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
     //關閉Statement
     public void close(Statement st) {
         if(st != null) {
             try {
                 st.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
   //關閉ResultSet
     public void close(ResultSet rs) {
         if(rs != null) {
             try {
                 rs.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }

}
