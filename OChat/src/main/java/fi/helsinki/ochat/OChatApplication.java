package fi.helsinki.ochat;

import fi.helsinki.ochat.dbservices.UserService;
import fi.helsinki.ochat.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@SpringBootApplication
public class OChatApplication extends WebSecurityConfigurerAdapter {
	@Autowired
    UserService userService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests(a -> a
				.antMatchers("/", "/error", "/webjars/**").permitAll()
				.anyRequest().authenticated()
			)
			.exceptionHandling(e -> e
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
			)
//			.csrf(c -> c
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//			)
				.csrf().disable().cors().disable()
			.logout(l -> l
				.logoutSuccessUrl("/").permitAll()
			)
			.oauth2Login()
				.successHandler((request, response, authentication) -> {
					System.out.println("loged in succesfully");
					OidcUserInfo userInfo = ((DefaultOidcUser)authentication.getPrincipal()).getUserInfo();
					String id = userInfo.getClaim("sub");
					String name = userInfo.getClaim("preferred_username");
					User u = new User(id, name);
					userService.register(u);
					response.sendRedirect("/");
				});
		// @formatter:on
	}

	public static void main(String[] args) {
		SpringApplication.run(OChatApplication.class, args);
	}

}