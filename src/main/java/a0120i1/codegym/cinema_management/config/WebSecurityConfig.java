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
                .antMatchers("/api/home").permitAll() // test TrongVT
                .antMatchers("/api/admin").hasRole("ADMIN") // test TrongVT
                .antMatchers("/api/user").hasRole("USER") // test TrongVT
                .antMatchers("/api/employee").hasRole("EMPLOYEE") // test TrongVT
                .antMatchers(HttpMethod.POST, "/api/login/**").permitAll() // TrongVT
                .antMatchers(HttpMethod.GET, "/api/users/account/generate/**").permitAll() // TrongVT
                .antMatchers(HttpMethod.POST, "/api/users/account/forgot-password").permitAll() // TrongVT
                .antMatchers(HttpMethod.PUT, "/api/users/account/password").hasAnyRole("USER", "ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/api/movie/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/movie/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/movie/**").permitAll() // NhuPTQ
                .antMatchers(HttpMethod.GET, "/api/movie/genre").permitAll()
                .antMatchers(HttpMethod.GET, "/api/movie/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/ticket/**").hasAnyRole("ADMIN","USER", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/ticket/**").hasAnyRole("ADMIN","USER", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/ticket/seat/**").hasAnyRole("USER", "EMPLOYEE") // NhuPTQ
                .antMatchers(HttpMethod.GET, "/api/booking").permitAll()
                .antMatchers(HttpMethod.GET, "api/booking/total-money").permitAll()
                .antMatchers(HttpMethod.GET, "/api/booking/list").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/booking/**").hasAnyRole("ADMIN","USER", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/booking/search").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/api/booking/**").hasRole("USER")  // NhuPTQ
                .antMatchers(HttpMethod.POST, "/api/booking/**").hasRole("USER") // NhuPTQ
                .antMatchers(HttpMethod.GET, "/api/employees").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/employees/id").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/employees").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
