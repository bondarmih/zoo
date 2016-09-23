package dev.bondarmih.sddt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by bondarm on 12.08.16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity httpSecurity) throws Exception {
//TODO modify security conf
        httpSecurity
                .authorizeRequests()
                .antMatchers("/app/home", "/app/login").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                .antMatchers("/app/admin").hasRole("WRITER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/app/login")
                .usernameParameter("user").passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/app/admin")
                .and()
                .csrf().disable();
    }

    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("WRITER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
