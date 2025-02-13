package com.ykkj.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.log.level.Level;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName SyncTable.java
 * @Description TODO
 * @createTime 2022年12月22日
 */
public class SyncTable {



    public static void main(String[] args) {

        //LogFactory
        DbUtil.setShowSqlGlobal(true, false,true, Level.INFO);
        for (int i = 0; i < 51; i++) {
            String dbName = "sdp";
            if(i==0){
                dbName = "sdp";
            }else {
                dbName = "sdp"+i;
            }
            System.out.println(dbName);

            //rdsa261960347g562ym4wo.mysql.rds.aliyuncs.com
//
            String dbName1 = "information_schema";
//            String url = "jdbc:mysql://rdsa261960347g562ym4wo.mysql.rds.aliyuncs.com:3306/information_schema?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
//            String user = "aqb";
//            String pass = "Yunkukeji_2015aqb";

            String url = "jdbc:mysql://192.168.1.107:3306/"+dbName1+"?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
            String user = "root";
            String pass = "root";

            DataSource dataSource = new SimpleDataSource(url,user,pass);

            try {
                //List<Entity> entityList = DbUtil.use(dataSource).findAll("demo");

                //DbUtil.use(dataSource).execute("ALTER TABLE demo ADD COLUMN `test1` varchar(255) NULL AFTER modify_date ");

                //DbUtil.use(dataSource).execute("ALTER TABLE demo DROP COLUMN test12");
                //DbUtil.use(dataSource).execute("use information_schema");

                String sql = "select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema = ? ";
                List<Entity> entityList = DbUtil.use(dataSource).query(sql,dbName);


                //System.out.println(entityList);
                for (Entity entity : entityList ) {
                    System.out.println(entity.getStr("data"));
                    //entity.getStr("data"),FileUtil.createTempFile(new File("F://sdp_data.txt"))
                    FileUtil.appendUtf8String(dbName+"="+entity.getStr("data")+"\r\n",FileUtil.file("F://ali_sdp_data.txt"));
                }
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
            DbUtil.close(dataSource);

        }



    }
}
