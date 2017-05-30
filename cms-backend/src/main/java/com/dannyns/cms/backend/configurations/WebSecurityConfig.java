package com.dannyns.cms.backend.configurations;

import com.dannyns.cms.backend.authentication.CookieOrHeaderSessionStrategy;
import com.dannyns.cms.backend.authentication.CustomAuthenticationEntryPoint;
import com.dannyns.cms.backend.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;

import static com.dannyns.cms.backend.CONST.DEFAULT_PAGE;
import static com.dannyns.cms.backend.CONST.LOGIN_PAGE;
import static com.dannyns.cms.backend.CONST.LOGOUT_PAGE;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final SessionRepositoryFilter sessionRepositoryFilter = new SessionRepositoryFilter(new MapSessionRepository());
        sessionRepositoryFilter.setHttpSessionStrategy(new CookieOrHeaderSessionStrategy());

        // stateless session
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        // disable csrf
        http
                .addFilterBefore(sessionRepositoryFilter, ChannelProcessingFilter.class)
                .csrf().disable();

        // config auth paths
        http.authorizeRequests()
                .antMatchers("/", "/rest/authentication/**", "/vendor/**", "/css/**", "/js/**", "/upload/**", "/images/**", "/fonts/**", "/**.html").permitAll()
                .antMatchers("/rest/**").authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_PAGE))
                .logoutSuccessUrl(DEFAULT_PAGE);

        // custom auth exception handling
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
