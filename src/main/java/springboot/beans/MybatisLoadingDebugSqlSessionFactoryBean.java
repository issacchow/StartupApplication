package springboot.beans;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.IOException;

public class MybatisLoadingDebugSqlSessionFactoryBean extends SqlSessionFactoryBean {
    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try {
            return super.buildSqlSessionFactory();
        }catch (Exception e){
            //在这里断点查看异常
            throw e;
        }
    }
}
