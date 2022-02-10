package com.hgun.sti.config;

import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources  =  {
                "/css/**",
                "/img/**",
                "/fonts/**",
                "/js/**",
        };

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/").permitAll()

                .antMatchers(HttpMethod.GET, "/paciente").permitAll()
                .antMatchers(HttpMethod.POST, "/paciente").permitAll()

                .antMatchers(HttpMethod.GET, "/paciente/chat").permitAll()
                .antMatchers(HttpMethod.POST, "/paciente/chat").permitAll()

                .antMatchers(HttpMethod.GET, "/filadeespera").permitAll()
                .antMatchers(HttpMethod.POST, "/filadeespera").permitAll()

                .antMatchers(HttpMethod.GET,"/administrador").hasAnyAuthority("ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, "/administrador").hasAnyAuthority("ADMINISTRADOR")

                .antMatchers(HttpMethod.POST,"/atendente" ).hasAnyAuthority("ATENDENTE")
                .antMatchers(HttpMethod.GET, "/atendente").hasAnyAuthority("ATENDENTE")

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(sucessoLogin())
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).logoutSuccessUrl("/").deleteCookies("JSESSIONID", "userID")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    private AuthenticationSuccessHandler sucessoLogin(){
        AuthenticationSuccessHandler atsh = new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                var usuario = usuarioRepository.getUsuarioByLogin(authentication.getName());

                Cookie cookie = new Cookie("userID", usuario.getId().toString());
                response.addCookie(cookie);

                var roles = usuario.getRoles();

                for (var role : roles) {
                    if(role.name.equals("ADMINISTRADOR")){
                        response.sendRedirect("/administrador/especialidade");
                    }else if(role.name.equals("ATENDENTE")){
                        response.sendRedirect("/atendente");
                    }
                }
            }
        };

        return atsh;
    }
}