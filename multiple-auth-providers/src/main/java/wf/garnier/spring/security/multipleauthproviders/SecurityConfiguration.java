package wf.garnier.spring.security.multipleauthproviders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("user")
                        .build()
        );
    }

    @Bean
    public AuthenticationProvider firstAuthenticationProvider() {
        return new AuthenticationProvider() {
            Logger logger = LoggerFactory.getLogger("first-authentication-provider");
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                logger.info("first-authentication-provider used");
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
    }

    @Bean
    public AuthenticationProvider secondAuthenticationProvider() {
        return new AuthenticationProvider() {
            Logger logger = LoggerFactory.getLogger("second-authentication-provider");
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                logger.info("second-authentication-provider used");
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
    }
}
