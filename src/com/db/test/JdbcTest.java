/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.db.test;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.db.api.JdbcCURD;
import com.udpserver.UDPServer;

public class JdbcTest {
    
    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {
    	final Logger log = Logger.getLogger(UDPServer.class);
    	
        JdbcCURD curd=new JdbcCURD();

        String sql = null;
        
//        //��ӱ�Teacher
//        sql="create table Teacher (Tid char(9) primary key,name char(9) unique)";
//        curd.createTable(sql);
//        
//        //���Ԫ��
//        sql = "insert into Teacher (Tid,name) values ('0001','Tom')";
//        curd.addElement(sql);
//        
//        //��ѯTeacher��
//        sql="select * from Teacher";
//        curd.Query(sql);
//        
//        //ɾ��Ԫ��
//        sql="delete from Teacher where Tid='0001'";
//        curd.removeElement(sql);
//        
//        //ɾ����Teacher
//        sql="drop table Teacher";
//        curd.dropTable(sql);
        String mdn ="18019398639";
        sql="select * from push_service where mdn=".concat(mdn);
        System.out.print(String.format("sql is: %s\n", sql));
        if (log.isInfoEnabled()) {
			log.info("sql is ".concat(sql));
		}

        String token=curd.QueryMdn(sql);
        System.out.print(String.format("token is: %s\n", token));
        if (log.isInfoEnabled()) {
			log.info("token is ".concat(token));
		}
    }

}