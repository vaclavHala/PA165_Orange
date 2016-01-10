package cz.muni.fi.pa165.dominatingspecies.web.config;

import javax.inject.Inject;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class DominatingSpeciesSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    private static final String USER_QUERY = "SELECT username, password, TRUE FROM usr WHERE username= ?";
    private static final String ROLE_QUERY = "SELECT username, name FROM role r join role_user ru on r.id = ru.fk_role join usr u on u.id = ru.fk_user where username = ?";

    @Inject
    private DataSource datasource;

    @Inject
    private PasswordEncoder passEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder passEncoder() {
        return new ShaPasswordEncoder(512);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(this.datasource)
                .usersByUsernameQuery(USER_QUERY)
                .passwordEncoder(this.passEncoder)
                .authoritiesByUsernameQuery(ROLE_QUERY);
    }

}
