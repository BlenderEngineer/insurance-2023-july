package pot.insurance.manager.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Repository;
import pot.insurance.manager.UserRepository;


/**
 * Attention: This is a security configuration ONLY for quick start and development purposes. It is
 * not secure and should NOT be used in production.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    UserRepository user;
    SecurityConfig(UserRepository repository) {
        user = repository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // This is for development purposes only! Patch for h2 console auth:
        http.csrf(csrfCustomizer ->
                csrfCustomizer.ignoringRequestMatchers(PathRequest.toH2Console())
        );
        http.headers(headersCustomizer ->
                headersCustomizer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );
        //
        http.authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/v1/echo").hasAnyRole("VIEWER", "ADMIN")
                .requestMatchers("/**").authenticated()
        )
                .httpBasic(customizer -> {
                    customizer.authenticationDetailsSource(source -> {
                        System.out.println(source);
                        return source;
                    });
                });
        //.httpBasic(withDefaults());
        return http.build();
    }

}