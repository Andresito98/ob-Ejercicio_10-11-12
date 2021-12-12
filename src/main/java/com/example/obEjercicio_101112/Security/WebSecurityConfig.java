package com.example.obEjercicio_101112.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/Saludo").permitAll() //Saludo le esta permitido el acceso pero las demas tendrian que logarse para que le funcionara.
                .antMatchers("/OtroSaludo").hasRole("ADMIN") //Al /OtroSaludo solo puedes acceder los admins
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
    }


    //Esto configura usuario y contraseña.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Estos usuarios se quedan en memoria no se guardan en base de datos ni nada.
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(passwordEncoder().encode("1234")).roles("USER") //Esto es para encriptar la contraseña -_- password(passwordEncoder().encode("password"))
                .and()
                .withUser("admin").password(passwordEncoder().encode("12345")).roles("USER", "ADMIN");
    }

    //Esto es para codificar/crifrar/encriptar contraseña.
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

}
