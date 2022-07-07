/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBUtils {

    public static final Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBConnect");
        con = ds.getConnection();
        return con;
    }
    
//        public static  Connection getConnection() throws ClassNotFoundException, SQLException{
//        Connection conn = null;
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url="jdbc:sqlserver://JONIMAY\\SQLEXPRESS:1433;databaseName=BookStore";
//        conn = DriverManager.getConnection(url,"sa","123456");  
//        return conn;
//    }
}
