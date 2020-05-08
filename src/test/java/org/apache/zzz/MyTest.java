package org.apache.zzz;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.zzz.dao.ProductDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTest.class);

    private static final  String RESOURCE_MYBATIS = "mybatis-hello.xml";
    private static final  String RESOURCE_SPRING = "spring-mybatis.xml";


    // 根据用户名称模糊查询用户列表
    @Test
    public void findProductByNameTest() throws IOException {
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(RESOURCE_MYBATIS);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // list中的user和映射文件中resultType所指定的类型一致
        List<Product> list = sqlSession.selectList("org.apache.zzz.dao.ProductDao.findProductByName", "burger");
        sqlSession.close();
        LOGGER.info(JSONObject.toJSONString(list));
    }

    @Test
    public void findProductByIdDaoTest() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(RESOURCE_SPRING);
        ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");
        Product product = productDao.findProductById(1);
        LOGGER.info(JSONObject.toJSONString(product));
    }

    @Test

    public void testFindUserById() throws IOException  {
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(RESOURCE_MYBATIS);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        // 调用mapper对象的方法
        Product product = productDao.findProductById(1);
        LOGGER.info(JSONObject.toJSONString(product));
        // 关闭SqlSession
        sqlSession.close();
    }


    @Test
    public void test(){
        System.out.println(AutoMappingBehavior.NONE);
    }


}
