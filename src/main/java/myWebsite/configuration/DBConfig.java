package myWebsite.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfig {

	@Autowired
	private ApplicationContext applicationContext;

	// application.properties의 DB 정보를 사용하도록 지정
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	// spring.datasource.hikari로 시작하는 히카리CP 설정 파일 생성
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	// application.properties의 설정 중 마이바티스 관련 설정을 가져옴
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
		// 가져온 마이바티스 설정을 자바 클래스로 만들어 반환
	}
	
	// 히카리CP 설정 파일을 이용해 DB와 연결하는 데이터 소스 생성
	@Bean
	public DataSource dataSource() throws Exception {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 위에서 만든 데이터 소스 설정
		sqlSessionFactoryBean.setDataSource(dataSource);
		// 마이바티스 매퍼 파일의 위치 설정
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/sql-**.xml"));
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	

}
