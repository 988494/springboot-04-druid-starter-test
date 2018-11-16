package druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(DruidProperties.class)//启动ConfigurationProperties，并把DruidProperties注册到容器中
public class DruidAutoConfiguration {
    private  DruidProperties druidProperties;

    //此构造函数，其中参数druidProperties会在容器中加载druidProperties
    public DruidAutoConfiguration(DruidProperties druidProperties) {
        this.druidProperties = druidProperties;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean<Servlet> druidServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), druidProperties.getUri());
        /*public static final String PARAM_NAME_USERNAME = "loginUsername";
        public static final String PARAM_NAME_PASSWORD = "loginPassword";
        public static final String PARAM_NAME_ALLOW = "allow";
        public static final String PARAM_NAME_DENY = "deny";
        public static final String PARAM_REMOTE_ADDR = "remoteAddress";
        */
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername",druidProperties.getUsername());
        initParameters.put("loginPassword",druidProperties.getPassword());
        initParameters.put("allow",druidProperties.getAllowIp());//默认允许所有ip访问
        // initParameters.put("deny",druidProperties.getDenyIp());
        bean.setInitParameters(initParameters);
        return bean;
    }
    //配置一个监控filter
    @Bean
    public FilterRegistrationBean<Filter> druidFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParameters = new HashMap<>();
//        public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
        initParameters.put("exclusions",druidProperties.getExclusions());
        bean.setInitParameters(initParameters);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}
