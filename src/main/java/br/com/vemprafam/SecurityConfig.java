package br.com.vemprafam;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	private boolean userExists(JdbcUserDetailsManager users, String username) {
        try {
            users.loadUserByUsername(username);
            return true;
        } catch (org.springframework.security.core.userdetails.UsernameNotFoundException e) {
            return false;
        }
    }
	@Autowired
    private DataSource dataSource;
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        // Define as consultas padrão que o JdbcUserDetailsManager usa
        users.setUsersByUsernameQuery(
        "select username, password, enabled from users where username=?");
        users.setAuthoritiesByUsernameQuery(
        "select username, authority from authorities where username=?");

		return users;
	}
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception {
		http.authorizeHttpRequests(auth->auth.requestMatchers("/")
				.permitAll().anyRequest().authenticated())
				.formLogin(login->login.permitAll())
				.logout(logout->logout.permitAll());
		return http.build();
	}
}









