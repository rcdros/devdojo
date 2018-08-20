package br.com.devdojo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by ricardors on 08/08/2018.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*/v2/api-docs",
                "/*/configuration/ui/**",
                "/*/swagger-resources/**",
                "/*/configuration/security/**",
                "/*/swagger-ui.html",
                "/*/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*/protected/**").hasRole("USER")
                .antMatchers("/*/admin/**").hasRole("ADMIN")
                .antMatchers(
                        HttpMethod.GET,
                        "/v2/api-docs","/swagger-ui.html", "/webjars/**", "favicon.ico"
                ).permitAll()
                .antMatchers("/auth/**").permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();//--desabilita CSRF error....
        //--"Could not verify the provided CSRF token because your session was not found
        // Cross-site request forgery CSRF
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ricardo").password("123456").roles("USER")
                .and()
                .withUser("admin").password("123456").roles("USER", "ADMIN");
    }
}
