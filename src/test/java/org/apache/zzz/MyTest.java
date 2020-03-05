package org.apache.zzz;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTest.class);

    private static final  String resource = "mybatis-hello.xml";


    // 根据用户名称模糊查询用户列表
    @Test
    public void findProductByNameTest() throws IOException {
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // list中的user和映射文件中resultType所指定的类型一致
        List<Product> list = sqlSession.selectList("test.findProductByName", "burger");
        sqlSession.close();
        LOGGER.info(JSONObject.toJSONString(list));
    }

    @Test
    public void test(){
        System.out.println(AutoMappingBehavior.NONE);
    }


}
