package br.com.llpassarelli.config;
 
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.llpassarelli.dao.TaskDao;
import br.com.llpassarelli.dao.TaskDaoImpl;
import br.com.llpassarelli.entity.Task;
 
@Configuration
@ComponentScan(basePackages = "br.com.llpassarelli.config")
@EnableTransactionManagement
public class TaskDbConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl("jdbc:postgresql://localhost:5432/senhas");
	    dataSource.setUsername("postgres");
	    dataSource.setPassword("admin");
	 
	    return dataSource;
	}
	
	private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    	return properties;
    }

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.addAnnotatedClasses(Task.class);
	    sessionBuilder.addProperties(getHibernateProperties());
	    sessionBuilder.scanPackages("br.com.llpassarelli.entity");
	    return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
	

	@Autowired
	@Bean(name = "TaskDao")
	public TaskDao getTaskDao(SessionFactory sessionFactory) {
	    return new TaskDaoImpl(sessionFactory);
	}
	
}
