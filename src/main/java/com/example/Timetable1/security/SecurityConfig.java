package com.example.Timetable1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

//@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("teacherRepositoryTeacherDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll().and().csrf().disable();
//                .antMatchers("/", "/Teachers_admin/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/Teachers/**").access("hasRole('ROLE_USER')")
//        http
//                .authorizeRequests()
////                .antMatchers("/", "/Teachers_admin", "/orders")
////                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/Teachers_admin/**", "/teachers/**").access("permitAll")
//                .antMatchers("/**").access("permitAll")
//
//                //end::authorizeRequests[]
//
//                .and()
//                .formLogin()
//                .loginPage("/Authorization")
//                    .loginProcessingUrl("/Authorization")
//                .usernameParameter("inputLogin")
//                .passwordParameter("inputPassword")
//                //.defaultSuccessUrl("/Teachers_admin")
//                //end::customLoginPage[]
//
//                // tag::enableLogout[]
//                .and()
//                .logout()
//                .logoutSuccessUrl("/Authorization")
//                // end::enableLogout[]
//
//                // Make H2-Console non-secured; for debug purposes
//                // tag::csrfIgnore[]
//                .and()
//                .csrf()
//                .ignoringAntMatchers("/h2-console/**")
//                // end::csrfIgnore[]
//
//                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
//                // tag::frameOptionsSameOrigin[]
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//        // end::frameOptionsSameOrigin[]
//
//        //tag::authorizeRequests[]
//        //tag::customLoginPage[]
//        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }
}
