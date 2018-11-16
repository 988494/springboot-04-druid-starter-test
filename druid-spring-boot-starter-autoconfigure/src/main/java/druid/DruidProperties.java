package druid;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "druid")
public class DruidProperties {
    private String username;//后台访问的用户名
    private String password;//后台访问的密码
    private String allowIp;//允许访问的地址
    private String denyIp;//禁止访问的地址
    private String uri;//后台地址
    private String exclusions;//过滤排除哪些资源  排除静态资源

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

    public String getAllowIp() {
        return allowIp;
    }

    public void setAllowIp(String allowIp) {
        this.allowIp = allowIp;
    }

    public String getDenyIp() {
        return denyIp;
    }

    public void setDenyIp(String denyIp) {
        this.denyIp = denyIp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }
}