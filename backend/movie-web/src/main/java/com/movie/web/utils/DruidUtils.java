package com.movie.web.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {

    private static DruidDataSource druidDataSource;

    static {
        try {
            String configPath = java.net.URLDecoder.decode(DruidUtils.class.getResource("").getPath(),"utf-8");
            System.out.println(configPath.substring(1)+"druid.properties");
            InputStream is = new FileInputStream(new File(configPath+"druid.properties"));
            Properties properties = new Properties();
            properties.load(is);
            druidDataSource = (DruidDataSource)
                    DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池数据源对象
     */
    public static DataSource getDataSource(){
        return druidDataSource;
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
