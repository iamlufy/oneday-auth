package com.oneday.auth;

import com.google.common.eventbus.EventBus;
import com.oneday.auth.authentication.domain.port.LoginUserRepositoryPort;
import com.oneday.core.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;


/**
 * @author zhuangzf
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.oneday.core","com.oneday.auth"})
@EnableRedisHttpSession
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//
//    @Configuration
//    @ConditionalOnProperty(prefix = "one.day.auth", name = "login.type",havingValue = "oauth2")
//    public static class OAuth2ServerConfig {
//        @Configuration
//        @EnableResourceServer
//        protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//            @Override
//            public void configure(ResourceServerSecurityConfigurer resources) {
//                resources.resourceId("123").stateless(true);
//            }
//
//            @Override
//            public void configure(HttpSecurity http) throws Exception {
//                // @formatter:off
//                http
//                        // Since we want the protected resources to be accessible in the UI as well we need
//                        // session creation to be allowed (it's disabled by default in 2.0.6)
//                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                        .and()
//                        .requestMatchers().anyRequest()
//                        .and()
//                        .anonymous()
//                        .and()
//                        .authorizeRequests()
////                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
//                        .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
//                // @formatter:on
//            }
//        }
//
//        @Configuration
//        @EnableAuthorizationServer
//        protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
//
//            @Autowired
//            AuthenticationManager authenticationManager;
//            @Autowired
//            RedisConnectionFactory redisConnectionFactory;
//
//            @Override
//            public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//                //配置两个客户端,一个用于password认证一个用于client认证
//                clients.inMemory().withClient("client_1")
//                        .resourceIds("123")
//                        .authorizedGrantTypes("client_credentials", "refresh_token")
//                        .scopes("select")
//                        .authorities("client")
//                        .secret("123456")
//                        .and().withClient("client_2")
//                        .resourceIds("123")
//                        .authorizedGrantTypes("password", "refresh_token")
//                        .scopes("select")
//                        .authorities("client")
//                        .secret("123456");
//            }
//
//            @Override
//            public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//                endpoints
//                        .tokenStore(new RedisTokenStore(redisConnectionFactory))
//                        .authenticationManager(authenticationManager);
//            }
//
//            @Override
//            public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//                //允许表单认证
//                oauthServer.allowFormAuthenticationForClients();
//            }
//
//        }
//
//        @Configuration
//        @EnableWebSecurity
//        public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//            @Bean
//            @Override
//            protected UserDetailsService userDetailsService(){
//                InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//                manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
//                manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
//                return manager;
//            }
//
//            @Override
//            protected void configure(HttpSecurity http) throws Exception {
//                // @formatter:off
//                http
//                        .requestMatchers().anyRequest()
//                        .and()
//                        .authorizeRequests()
//                        .antMatchers("/oauth/*").permitAll();
//                // @formatter:on
//            }
//        }
//
//
//    }
//
//
//    @Configuration
//    @ConditionalOnProperty(prefix = "one.day.auth", name = "login.type",matchIfMissing = true)
//    public static class OAuth2ServerConfi1g {
//        public OAuth2ServerConfi1g() {
//            System.out.println("12312");
//        }
//    }





}
