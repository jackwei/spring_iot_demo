package mybatisTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class CacheTest {

    public void testFirstLevelCacheUseSameSqlSession() throws IOException {
        Reader mysqlReader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mysqlReader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 第一次执行查询
//        UserOrderMapper userOrderMapper = sqlSession.getMapper(UserOrderMapper.class);
//        System.out.println("第一次查询，使用【userOrderMapper】执行查询");
//        UserOrderDO userOrder = userOrderMapper.selectByOrderNo("D202405082208045788");
//        System.out.println("第一次查询结果：" + JSON.toJSONString(userOrder));
//
//        // 第二次执行查询
//        UserOrderMapper newUserOrderMapper = sqlSession.getMapper(UserOrderMapper.class);
//        System.out.println("第二次查询，使用【newUserOrderMapper】执行查询");
//        UserOrderDO newUserOrder = newUserOrderMapper.selectByOrderNo("D202405082208045788");
//        System.out.println("第二次查询结果：" + JSON.toJSONString(userOrder));
    }

}
