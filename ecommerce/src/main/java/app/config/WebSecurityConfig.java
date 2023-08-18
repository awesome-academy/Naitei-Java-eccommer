package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import app.authentication.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// For User in database.
		auth.userDetailsService(myDBAauthenticationService);

	}

	private static final String[] AUTH_WHITELIST = { "/", "/login", "/logout", "/logoutSuccessful", "/signup",
			"/assets/**", "/products/**", "/blogs/**", "/shop-grid" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')").anyRequest().access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')").and()
				.formLogin().loginProcessingUrl("/process-login").loginPage("/login").defaultSuccessUrl("/")
				.failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password").and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful").and().exceptionHandling()
				.accessDeniedPage("/login");
	}
}