package a0120i1.codegym.cinema_management.config;

import a0120i1.codegym.cinema_management.security.filter.JwtRequestFilter;
import a0120i1.codegym.cinema_management.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private MyUserDetailsService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/home").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login/google").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login/facebook").permitAll()
                .antMatchers("/api/admin").hasRole("ADMIN")
                .antMatchers("/api/user").hasRole("USER")
                .antMatchers("/api/employee").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/users/account/password").hasAnyRole("USER","ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.POST,"/api/movie/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/movie/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/movie/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/ticket/**").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.GET,"/api/ticket/**").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.GET,"/api/booking").permitAll()
                .antMatchers(HttpMethod.GET,"api/booking/total-money").permitAll()
                .antMatchers(HttpMethod.GET,"/api/booking/**").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.GET,"/api/booking/search").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.GET,"/api/users/account/generate/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/users/account/forgot-password/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
