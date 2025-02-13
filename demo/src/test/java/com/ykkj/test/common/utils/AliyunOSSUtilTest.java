package com.ykkj.test.common.utils;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.ykkj.utils.DESUtil;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName AliyunOSSUtilTest.java
 * @Description TODO
 * @createTime 2022年12月27日
 */
public class AliyunOSSUtilTest {

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    static String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    static String accessKeyId =  DESUtil.getDecryptString("10sTidzuchzpwvAhuAHPPR8kB9Ww/4zb");
    static String accessKeySecret = DESUtil.getDecryptString("IUDLmnRG1iIzDwKWGsh4BaD3dZkQa4rmBZ1+7ClQmzA=");
    // 填写Bucket名称，例如examplebucket。
    static String bucketName = "ykkj-aqqgcgk-file";

    public static void main(String[] args) throws SQLException {

        //SysCurriculum/910146368775913472/2021-11-16/a92cf5d0336b40109e04e8fa512efb8f.ppt
        String pathName = "F:\\localpath\\电焊与气焊安全作业02.mp4";
        String ss = "SysCurriculum/912856202981933056/2021-11-24/a1aec427a9e742cb85876b06b72ade75.mp4";
        //download(ss,pathName);
        //upload();

        findFileDownload();
    }

    public static void findFileDownload() throws SQLException {
        String dbName = "sdp";
        String url = "jdbc:mysql://192.168.1.107:3306/"+dbName+"?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&tinyInt1isBit=false";
        String user = "root";
        String pass = "root";

        DataSource dataSource = new SimpleDataSource(url,user,pass);
        String sql = "select * from sys_curriculum_detail,sys_curriculum where scd_sc_id = sc_id and sc_uc_id and sc_uc_id = ? ";

        List<Entity> curriculumList = DbUtil.use(dataSource).query(sql,"832537425933762560");

        for (Entity entity : curriculumList) {
            System.out.println(entity.getStr("scd_file_name"));
            System.out.println(entity.getStr("scd_file_url"));
            String pathName = "F:\\localpath\\"+entity.getStr("scd_file_name");
            download(entity.getStr("scd_file_url"),pathName);

        }
    }



    //
    public static void upload(){

        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String key = "SysCurriculum/910145223152107520/2021-11-16/14665af082814871bb05c208235062cd.ppt";

        // 创建OSSClient实例。
        // OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            /**
             * Note that there are two ways of uploading an object to your bucket, the one
             * by specifying an input stream as content source, the other by specifying a file.
             */

            /*
             * Upload an object to your bucket from an input stream
             */
            System.out.println("Uploading a new object to OSS from an input stream\n");
            String content = "Thank you for using Aliyun Object Storage Service";
            client.putObject(bucketName, key, new ByteArrayInputStream(content.getBytes()));

            /*
             * Upload an object to your bucket from a file
             */
            System.out.println("Uploading a new object to OSS from a file\n");
            client.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));

            /*
             * Download an object from your bucket
             */
            //System.out.println("Downloading an object");
            //OSSObject object = client.getObject(new GetObjectRequest(bucketName, key));
            //System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
            //displayTextInputStream(object.getObjectContent());

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }


    public static void download(String objectName,String pathName){
        // 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        //String objectName = "SysCurriculum/910145223152107520/2021-11-16/14665af082814871bb05c208235062cd.ppt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
            // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(pathName));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-323232323", ".ppt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("\n");
        writer.write("\n");
        writer.close();

        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("\t" + line);
        }
        System.out.println();

        reader.close();
    }


}
