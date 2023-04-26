package cl.leo.apieclipse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private JwtAuthEntryPoint authEntryPoint;

	@Autowired
	public SecurityConfig(JwtAuthEntryPoint authEntryPoint) {
		super();
		this.authEntryPoint = authEntryPoint;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	JwtAuthFilter authFilter() {
		return new JwtAuthFilter();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(authEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/api/auth/**").permitAll()
			.requestMatchers(HttpMethod.GET,"/api/auto/listar").hasAnyAuthority("USER","ADMIN")
            .requestMatchers(HttpMethod.GET,"/api/auto/listar/**").hasAnyAuthority("USER","ADMIN")
			.requestMatchers(HttpMethod.POST,"/api/auto/crear").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.PUT,"/api/auto/actualizar").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/api/auto/eliminar/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.httpBasic();
		http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
