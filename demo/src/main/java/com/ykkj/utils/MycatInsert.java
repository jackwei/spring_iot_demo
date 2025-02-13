package com.ykkj.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.ds.simple.SimpleDataSource;
import org.springframework.web.util.UriUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName MycatInsert.java
 * @Description TODO
 * @createTime 2023年02月01日
 */
public class MycatInsert {

    public static void main(String[] args) throws SQLException {


        String url = "jdbc:mysql://192.168.1.107:3306/sdp?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
        String user = "root";
        String pass = "root";

        DataSource dataSource = new SimpleDataSource(url,user,pass);

        String sql = "INSERT INTO `mycat_partition_id`(`partition_id`, `state`) VALUES (?, 1);";

        for (int i = 1; i <= 20; i++) {

            int result = Db.use(dataSource).execute(sql, IdUtil.getSnowflakeNextId());
            System.out.println(result);
        }

        //UriUtils.encode();


        //int result = Db.use(dataSource).execute(sql,System.currentTimeMillis(),611856743546224640L,new Date(),new Date());


        DbUtil.close(dataSource);

    }

    public void insertTest() throws SQLException {
        String url = "jdbc:mysql://192.168.1.198:8066/TESTDB?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&tinyInt1isBit=false&useLocalSessionState=true";
        String user = "mycat";
        String pass = "Yunkukeji_2015aqb";

        DataSource dataSource = new SimpleDataSource(url,user,pass);

        String sql = "INSERT INTO `fk_safe_project`(`id`, `fsp_uc_id`, `fsp_name`, `fsp_type`, `fsp_address`, `fsp_start_date`, `fsp_end_date`, `fsp_profession`, `fsp_team`, `fsp_risk_level`, `fsp_frl_id`, `fsp_executing`, `fsp_eep_state`, `create_user_id`, `modify_user_id`, `create_date`, `modify_date`, `fsp_ext_uc_id`, `fsp_ext_uc_name`, `create_user_name`, `modify_user_name`, `fsp_uc_name`, `fsp_comment_status`) VALUES (?, ?, '660MW#3高含盐废水泵盘根更换', 'JOB_OVERHAUL', '660MW高含盐泵房', '2023-01-31 15:30:00', '2023-01-31 23:30:00', '输煤专业', '输煤检修班', 'LOW', NULL, 'YES', 'NO', 697892230743982080, 697892230743982080, ?, ?, 681528918238298112, '山东德源机电设备检修运营有限公司店塔项目部', '李恒', '李恒', '店塔', 0) ";

        int result = Db.use(dataSource).execute(sql,System.currentTimeMillis(),202302L,new Date(),new Date());
        //int result = Db.use(dataSource).execute(sql,System.currentTimeMillis(),611856743546224640L,new Date(),new Date());
        System.out.println(result);

        DbUtil.close(dataSource);
    }
}
