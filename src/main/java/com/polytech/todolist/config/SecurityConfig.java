package com.polytech.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        //bdd local
        //auth.InMemoryAuthentication().withUser("geoffrey").password("password");

       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .mvcMatchers("/admin/*").hasRole("Admin")
                .mvcMatchers("/","/register.html","/login.html").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login.html")
                .and().csrf().disable();

        /*http.authorizeRequests()
                .anyRequest().authenticated()
                .mvcMatchers("/admin/*").hasRole("Admin")
                .mvcMatchers("about","help","register").permitAll()
                .and().formLogin().loginPage("/landing.html").permitAll()
                .defaultSuccessUrl("/",true)
                .and().csrf().disable();*/
    }

}
