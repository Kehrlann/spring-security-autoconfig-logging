package wf.garnier.spring.security.twouserdetailsservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public UserDetailsService otherUserDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("other-user")
                        .password("{noop}password")
                        .roles("user")
                        .build()
        );
    }

}
