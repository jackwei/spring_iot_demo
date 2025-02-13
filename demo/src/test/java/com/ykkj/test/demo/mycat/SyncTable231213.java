package com.ykkj.test.demo.mycat;

import cn.hutool.db.DbUtil;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.log.level.Level;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName SyncTable.java
 * @Description TODO
 * @createTime 2022年12月22日
 */
public class SyncTable231213 {



    public static void main(String[] args) {

        //singleDb();
        batchExecuteAllDb();
    }

    public static void batchExecuteAllDb(){

        //LogFactory
        DbUtil.setShowSqlGlobal(true, false,true, Level.INFO);
        for (int i = 1; i < 71; i++) {
            String dbName = "sdp";
            if(i==0){
                dbName = "sdp";
                //continue;
            }else {
                dbName = "sdp"+i;
            }
            System.out.println(dbName);

            //rdsa261960347g562ym4wo.mysql.rds.aliyuncs.com
//
//            String url = "jdbc:mysql://rdsa261960347g562ym4wo.mysql.rds.aliyuncs.com:3306/"+dbName+"?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
//            String user = "aqb";
//            String pass = "Yunkukeji_2015aqb";


            String url = "jdbc:mysql://192.168.1.107:3306/"+dbName+"?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
            String user = "root";
            String pass = "root";


            DataSource dataSource = new SimpleDataSource(url,user,pass);

            try {
                //List<Entity> entityList = DbUtil.use(dataSource).findAll("demo");
//                for (Entity entity : entityList ) {
//                    System.out.println(entity.getStr("username"));
//                }


                DbUtil.use(dataSource).execute("ALTER TABLE `fk_safe_project_study`\n" +
                        "    ADD COLUMN `fsps_job_content` varchar(2000) NULL COMMENT '工作内容' AFTER `fsps_uc_name`");

                DbUtil.use(dataSource).execute("ALTER TABLE `fk_safe_project`\n" +
                        "    ADD COLUMN `fsp_job_name` varchar(2000) NULL COMMENT '作业名称' AFTER `fsp_comment_status`");

                DbUtil.use(dataSource).execute("ALTER TABLE `fk_safe_project_real`\n" +
                        "    ADD COLUMN `fsp_job_name` varchar(2000) NULL COMMENT '作业名称' AFTER `fsp_comment_status`");
//                DbUtil.use(dataSource).execute("ALTER TABLE `us_company`\n" +
//                        "ADD COLUMN `uc_gastank_status` tinyint(2) NULL DEFAULT 0 COMMENT '是否开启气罐管理  开启为1 不开启为0 (电厂)' AFTER `uc_sms_push`");

                System.out.println("========="+dbName+"===========");



                //System.out.println(entityList);

            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
            DbUtil.close(dataSource);

        }
    }

    public static void  singleDb(){
        //LogFactory
        DbUtil.setShowSqlGlobal(true, false,true, Level.INFO);

        String dbName = "sdp1";
        String url = "jdbc:mysql://192.168.1.107:3306/"+dbName+"?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
        String user = "root";
        String pass = "root";


        DataSource dataSource = new SimpleDataSource(url,user,pass);

        try {

            DbUtil.use(dataSource).execute("ALTER TABLE `us_company` \n" +
                    "ADD COLUMN `uc_sms_push` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启短信推送  开启为1 不开启为0 (电厂)' AFTER `uc_work_user_check`");
//            DbUtil.use(dataSource).execute(delTable("gt_gastank"));
//            int row = DbUtil.use(dataSource).execute(crateTableGastank());
//            System.out.println("execute == " + row);
//
//            DbUtil.use(dataSource).execute(delTable("gt_gastank_inspection"));
//            int rowGTI = DbUtil.use(dataSource).execute(crateTableGastankInspection());
//            System.out.println("execute == " + rowGTI);

            //System.out.println(entityList);

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        DbUtil.close(dataSource);
    }


    public static String crateTableGastank(){
        return  "CREATE TABLE `gt_gastank` (\n" +
                "  `id` bigint(20) NOT NULL DEFAULT '0',\n" +
                "  `code` varchar(20) DEFAULT '' COMMENT '气罐编码',\n" +
                "  `location` varchar(200) DEFAULT '' COMMENT '气罐位置',\n" +
                "  `status` varchar(50) DEFAULT 'MG' COMMENT '气罐状态',\n" +
                "  `type` varchar(50) DEFAULT '' COMMENT '气罐型号',\n" +
                "  `production_unit` varchar(200) DEFAULT '' COMMENT '制造单位',\n" +
                "  `production_time` datetime DEFAULT NULL COMMENT '制造时间',\n" +
                "  `arrival_time` datetime DEFAULT NULL COMMENT '入厂时间',\n" +
                "  `service_life` varchar(200) DEFAULT '' COMMENT '使用寿命',\n" +
                "  `uc_id` bigint(20) DEFAULT NULL COMMENT '公司id',\n" +
                "  `uc_name` varchar(100) DEFAULT NULL COMMENT '公司名称',\n" +
                "  `remark` varchar(2550) DEFAULT NULL COMMENT '备注',\n" +
                "  `fzr_name` varchar(255) DEFAULT NULL COMMENT '负责人名称',\n" +
                "  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',\n" +
                "  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',\n" +
                "  `create_date` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT";
    }


    public static String crateTableGastankInspection(){
        return  "CREATE TABLE `gt_gastank_inspection` (\n" +
                "  `id` bigint(20) NOT NULL DEFAULT '0',\n" +
                "  `gt_id` bigint(20) DEFAULT '0',\n" +
                "  `gt_code` varchar(20) DEFAULT '' COMMENT '气罐编码',\n" +
                "  `insp_location` varchar(200) DEFAULT '' COMMENT '气罐位置',\n" +
                "  `insp_date` datetime DEFAULT NULL COMMENT '巡检日期',\n" +
                "  `insp_user_id` bigint(20) DEFAULT NULL COMMENT '巡检人员id',\n" +
                "  `insp_user_name` varchar(50) DEFAULT '' COMMENT '巡检人员名称',\n" +
                "  `insp_status` varchar(20) DEFAULT '' COMMENT '气罐状态',\n" +
                "  `uc_id` bigint(20) DEFAULT NULL COMMENT '公司id',\n" +
                "  `uc_name` varchar(100) DEFAULT NULL COMMENT '公司名称',\n" +
                "  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',\n" +
                "  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',\n" +
                "  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',\n" +
                "  `create_date` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT";
    }


    public static String delTable(String table){
        return "DROP TABLE IF EXISTS `"+table+"` ";
    }

}
