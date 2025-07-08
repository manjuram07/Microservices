//package com.EMS;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(
//                        auth -> auth
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/user/**").hasRole("USER")
//                                .anyRequest().authenticated()
//                )
//                .formLogin(login -> login.permitAll())
//                .logout(logout -> logout.permitAll());
//
//        return http.build();
//
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/login", "/resources/**", "/h2-console/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .formLogin(form -> form
////                        .loginPage("/login")             // custom login page URL
////                        .loginProcessingUrl("/perform_login") // URL for processing login credentials
////                        .defaultSuccessUrl("/homepage", true)
////                        .failureUrl("/login?error=true")
////                        .permitAll()
////                )
////                .logout(logout -> logout
////                        .logoutUrl("/perform_logout")
////                        .logoutSuccessUrl("/login?logout=true")
////                        .deleteCookies("JSESSIONID")
////                );
////        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//        UserDetails user = users
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = users
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//
//    }
//
////    @Bean
////    public UserDetailsManager users(DataSource dataSource) {
////        return new JdbcUserDetailsManager(dataSource);
////    }
//
//}
//
//
