package gesjana.ikub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.*;

// TODO: Auto-generated Javadoc

@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("gesjana")
                .password("gesjana123")
                .roles("ADMIN");
    }
}
