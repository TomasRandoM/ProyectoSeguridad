package com.ntm.clienteadministrativo.security;


import com.ntm.clienteadministrativo.services.UsuarioDTOService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SeguridadWeb{
    @Autowired
    private UsuarioDTOService usuarioService;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
        auth.userDetailsService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/empleado/**", "/pais/**", "/provincia/**", "/localidad/**", "/departamento/**", "/direccion/**", "/planillaHoraria/list", "/planillaHoraria/baja", "/planillaHoraria/modificar").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/usuario/**", "/imagen/**", "/servicio/**", "/unidadDeNegocio/**", "/empresa/**", "/contactoCorreoElectronico/**", "/contactoTelefonico/**", "/cuentaCorreo/**").hasRole("SUPERADMIN")
                        .requestMatchers("/habitante/**", "/inmueble/**", "/planillaHoraria/presente", "/planillaHoraria/salida", "/visitante/list", "/movimientoVisita/list", "/visitante/modificar", "/visitante/baja", "movimientoVisita/modificar", "movimientoVisita/baja").hasAnyRole("ADMIN", "SUPERADMIN", "PERSONAL")
                        .requestMatchers("/css/**", "/js/**", "/assets/**", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login/send")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/inicio")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .exceptionHandling((exception)-> exception.accessDeniedPage("/"))
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}
