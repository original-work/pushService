/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.db.driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class JdbcUtils {

    private static String driverName;
    private static String url;
    private static String user;
    private static String password;
    final static Logger log = Logger.getLogger(JdbcUtils.class);
    /*
     * ��̬����飬���ʼ��ʱ�������ݿ�����
     */
    static {
        try {
            // ����dbinfo.properties�����ļ�
//        	String resPath = JdbcUtils.class.getResource("/dbinfo.properties").getPath();
//        	System.out.println("resPath: " + resPath);
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbinfo.properties");
            Properties properties = new Properties();
            properties.load(in);

            // ��ȡ�������ơ�url���û����Լ�����
            driverName = properties.getProperty("driverName");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            // ��������
            Class.forName(driverName);
            
        } catch (IOException e) {
        	log.error(e.toString());
        } catch (ClassNotFoundException e) {
        	log.error(e.toString());
        }
    }

    /*
     * ��ȡ����
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, password);

    }

    /*
     * �ͷ���Դ
     */
    public static void releaseResources(ResultSet resultSet,
            Statement statement, Connection connection) {

        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
        	log.error(e.toString());
        } finally {
            resultSet = null;
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
            	log.error(e.toString());
            } finally {
                statement = null;
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                	log.error(e.toString());
                } finally {
                    connection = null;
                }
            }
        }

    }

}