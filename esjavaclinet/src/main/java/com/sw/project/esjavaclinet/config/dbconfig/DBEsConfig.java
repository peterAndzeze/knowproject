package com.sw.project.esjavaclinet.config.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 缴费服务db
 */
@Configuration
@MapperScan(basePackages = "com.esjavaclient.**.mapper",sqlSessionFactoryRef ="sqlSessionFactory")
public class DBEsConfig {
    private static final int TX_METHOD_TIMEOUT = 5;

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datatource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;



    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasoruce")
    public DruidDataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory businessSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*/*.xml"));
        return sessionFactory.getObject();
    }




    /*private static final String   CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME               = "customizeTransactionInterceptor";
    *//**
     * 默认只对 "*Service" , "*ServiceImpl" Bean 进行事务处理,"*"表示模糊匹配, 比如 : userService,orderServiceImpl
     *//*
    private static final String[] DEFAULT_TRANSACTION_BEAN_NAMES                       = { "*Service" , "*ServiceImpl" };
    *//**
     * 可传播事务配置
     *//*
    private static final String[] DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES  = {
            "add*" ,
            "save*" ,
            "insert*" ,
            "delete*" ,
            "update*" ,
            "edit*" ,
            "batch*" ,
            "create*" ,
            "remove*" ,
    };
    *//**
     * 默认的只读事务
     *//*
    private static final String[] DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
            "get*" ,
            "count*" ,
            "find*" ,
            "query*" ,
            "select*" ,
            "list*" ,
            "*" ,
    };
    *//**
     * 自定义事务 BeanName 拦截
     *//*
    private              String[] customizeTransactionBeanNames                        = {};
    *//**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法; 只读事务
     *//*
    private              String[] customizeReadOnlyMethodRuleTransactionAttributes     = {};
    *//**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法;
     * 传播事务(默认的){@link org.springframework.transaction.annotation.Propagation#REQUIRED}
     *//*
    private              String[] customizeRequiredMethodRuleTransactionAttributes     = {};


    *//**
     * 配置事务拦截器
     *
     * @param transactionManager : 事务管理器
     *//*
    @Bean( CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME )
    public TransactionInterceptor customizeTransactionInterceptor ( PlatformTransactionManager transactionManager ) {
        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute       readOnly                   = this.readOnlyTransactionRule();
        RuleBasedTransactionAttribute       required                   = this.requiredTransactionRule();
        // 默认的只读事务配置
        for ( String methodName : DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES ) {
            transactionAttributeSource.addTransactionalMethod( methodName , readOnly );
        }
        // 默认的传播事务配置
        for ( String methodName : DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES ) {
            transactionAttributeSource.addTransactionalMethod( methodName , required );
        }
        // 定制的只读事务配置
        for ( String methodName : customizeReadOnlyMethodRuleTransactionAttributes ) {
            transactionAttributeSource.addTransactionalMethod( methodName , readOnly );
        }
        // 定制的传播事务配置
        for ( String methodName : customizeRequiredMethodRuleTransactionAttributes ) {
            transactionAttributeSource.addTransactionalMethod( methodName , required );
        }
        return new TransactionInterceptor( transactionManager , transactionAttributeSource );
    }

    *//**
     * 配置事务拦截
     * <p>
     * {@link #customizeTransactionInterceptor(PlatformTransactionManager)}
     *//*
    @Bean
    public BeanNameAutoProxyCreator customizeTransactionBeanNameAutoProxyCreator () {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        // 设置定制的事务拦截器
        beanNameAutoProxyCreator.setInterceptorNames( CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME );
        // 默认
        for ( String defaultTransactionBeanNameSuffix : DEFAULT_TRANSACTION_BEAN_NAMES ) {
            beanNameAutoProxyCreator.setBeanNames( defaultTransactionBeanNameSuffix );
        }
        // 定制
        for ( String customizeTransactionBeanName : customizeTransactionBeanNames ) {
            beanNameAutoProxyCreator.setBeanNames( customizeTransactionBeanName );
        }
        beanNameAutoProxyCreator.setProxyTargetClass( true );
        return beanNameAutoProxyCreator;
    }

    *//**
     * 支持当前事务;如果不存在创建一个新的
     * {@link org.springframework.transaction.annotation.Propagation#REQUIRED}
     *//*
    private RuleBasedTransactionAttribute requiredTransactionRule () {
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setRollbackRules( Collections.singletonList( new RollbackRuleAttribute( Exception.class ) ) );
        required.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED );
        required.setTimeout( TransactionDefinition.TIMEOUT_DEFAULT );
        return required;
    }

    *//**
     * 只读事务
     * {@link org.springframework.transaction.annotation.Propagation#NOT_SUPPORTED}
     *//*
    private RuleBasedTransactionAttribute readOnlyTransactionRule () {
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly( true );
        readOnly.setPropagationBehavior( TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        return readOnly;
    }*/




   /* @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        *//*只读事务，不做更新操作*//*
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        *//*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*//*
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        source.setNameMap( txMap );
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager(), source);
        return txAdvice;
    }
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
        //return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }*/






    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }


}
