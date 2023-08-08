package com.progress.test.util;

//import com.progress.test.jwt.JwtTokenFilter;
//import com.progress.test.service.impl.AuthServiceImpl;
import com.progress.test.service.impl.UserServiceImpl;
//import com.progress.test.service.process.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
/*@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

 */
public class SecurityConfig {


    /*private final UserDetailsService userDetailsService;


    private final UserServiceImpl userServiceImpl;

    private final JwtTokenUtil jwtTokenUtil;



    @Autowired
    public  SecurityConfig(UserServiceImpl userServiceImpl, JwtTokenUtil jwtTokenUtil) {
        this.userServiceImpl = userServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
    }


     */


    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(userServiceImpl, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

     */


}
