package com.example.message.security;

import com.example.message.configuration.MessageAPIInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    public void setRestAuthenticationEntryPoint(final RestAuthenticationFailureHandler restAuthenticationEntryPoint) {
        this.restAuthenticationFailureHandler = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and().csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, MessageAPIInfo.BASE_PATH + "/login").permitAll()
                .antMatchers(HttpMethod.GET, MessageAPIInfo.MESSAGE_BASE_PATH + "/**").permitAll()
                .antMatchers(HttpMethod.GET,MessageAPIInfo.USER_BASE_PATH + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // filter login requests
                .addFilterBefore(new JWTLoginFilter(MessageAPIInfo.BASE_PATH + "/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //filter other requests to verify JWT Token
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("ad123"))
                .roles("ADMIN")
                .and().withUser("user")
                .password(passwordEncoder.encode("us123"))
                .roles("USER")
                .and().withUser("adminuser")
                .password(passwordEncoder.encode("adus123"))
                .roles("ADMIN", "USER");

    }

}
