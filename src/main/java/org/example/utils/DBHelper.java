package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    
    static final Properties properties = new Properties();
    static {
        try{
            InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
        }catch(IOException e){
            System.out.println("PropertiesUtils读取文件错误");
        }
    }
    public static final String url =  properties.getProperty("biz.jdbc.url");
    public static final String name = properties.getProperty("biz.jdbc.driverClassName");  
    public static final String user = properties.getProperty("biz.jdbc.username");  
    public static final String password = properties.getProperty("biz.jdbc.password");  


    public Connection conn = null;
    public PreparedStatement pst = null;
  
    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接
            
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            System.out.println("连接数据库出现异常:"+e);
        }
    }
     
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();
            System.out.println("关闭数据库连接");
        } catch (SQLException e) {
            System.out.println("关闭数据库连接发生错误:"+e);
        }  
    }  
}  