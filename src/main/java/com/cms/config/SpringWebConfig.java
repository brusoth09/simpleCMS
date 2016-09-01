package com.cms.config;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * class to configure application and beans. replace spring beans xml configurations file.
 *
 * @author burusothman a
 * @version 0.1
 * @Configuration annotation let spring knows this class contains bean definitions.
 * @EnableWebMvc annotation is same as <mvc:annotation-driven/>
 * @ComponentScan annotation is same as <context:component-scan base-package="com.atutus.app"/>
 */
@EnableWebMvc
@Configuration
@PropertySource("classpath:database/database.properties")
@ComponentScan({"com.cms.app"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /**
     * Method used to define our static resources like assets, css, images and java script files.
     *
     * @param registry optimized registry for serving static resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap/");
        registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }

    /**
     * @return
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520);   // 20MB
        multipartResolver.setMaxInMemorySize(1048576);  // 1MB
        return multipartResolver;
    }

    /**
     * Method to register view controllers.
     *
     * @param registry stores registrations of view controllers.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/index.jsp");
    }

    /**
     * Bean for internal view resolver. Internal view Resolver is used to find internal resource view
     * like jsp and html using predefined URL patterns. Additionally it allows to add some predefined
     * prefix and suffix to view name and generate final view page URL.
     *
     * @return internal view resolver.
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    /**
     * Bean class for Message Source.
     *
     * @return message source object
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/messages");
        return messageSource;
    }

    /**
     * Bean class for FlyWay database migration library.
     *
     * @return flyway object
     */
    @Bean(initMethod = "migrate")
    public Flyway getFlyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("/flyway/");
        flyway.setDataSource(getDataSource());

        return flyway;
    }

    /**
     * Bean class for data source object which is used to conect the database.
     *
     * @return data source
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    /**
     * Bean class for property sources place holder.
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Method to get hibernate session factory
     *
     * @param dataSource data source bean
     * @return session factory
     */
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("com.cms.app.model");
        sessionBuilder.setProperty("hibernate.show_sql", "false");
        return sessionBuilder.buildSessionFactory();
    }

    /**
     * Method to get hibernate transaction manager
     *
     * @param sessionFactory session factory
     * @return transaction manager
     */
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
