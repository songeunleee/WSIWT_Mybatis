# WSIWT_Mybatis

> SQL을 실전에서 사용해 보고자 JPA를 Mybatis로 변경

1. 의존성 추가
    - build.gradle
    
    `implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0'`
    
2. aplication.yaml 파일 
    
    ```yaml
      mybatis:
        mapper-locations: classpath:mappers/*.xml
    ```
    
    - resources 파일 하위에 mappers파일 안에 있는 xml파일을 mapper로 인식
3. mybatisConfig
    
    ```java
    @Configuration
    @MapperScan   // mapper 인터페이스의 경로와 동일해야 한다.
    @RequiredArgsConstructor
    public class MybatisConfig {
    
        private final ApplicationContext applicationContext;
    
        @Bean
        public SqlSessionFactory sqlSessionFactory (DataSource dataSource) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    
            sqlSessionFactoryBean.setDataSource(dataSource);
             sqlSessionFactoryBean.setMapperLocations(
                    applicationContext.getResources("classpath:/mappers/*.xml"
                    ));
    
            return sqlSessionFactoryBean.getObject();
        }
    
        @Bean
        public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }
    ```
    
    - mybatis stater의존성을 사용하면 mybatisConfig를 작성하지 않아도 된다고 그랬는데 mapper파일을 계속 인식하지 못해서 작성
