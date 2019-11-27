package com.example.Project1;

import com.example.Project1.services.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter
    {

        private UserPrincipalDetailsService userDetailsService;

        private DataSource dataSource;

        public SecurityConfig(UserPrincipalDetailsService userDetailsService, DataSource dataSource) {
            this.userDetailsService = userDetailsService;
            this.dataSource = dataSource;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()


                    .antMatchers("/").hasRole("admin")
                    .antMatchers("/allCompanies").hasAnyRole("admin", "companyManager")
                    .antMatchers("/allBusinessUnits").hasAnyRole("admin", "companyManager", "businessUnitManager")
                    .antMatchers("/allDepartments").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager")
                    .antMatchers("/allUnits").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager")
                    .antMatchers("/allEmployees").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","employee")
                    .antMatchers("/createEmployee").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager")
                    .antMatchers("/updateEmployee").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager")
                    .antMatchers("/getEmployeeBySearchCriteria").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","employee")
                    .and()
                    .httpBasic();



        }



        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth)
                throws Exception
        {
            auth.authenticationProvider(authenticationProvider());
        }



        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }



        @Bean
        DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            daoAuthenticationProvider.setUserDetailsService(userDetailsService);

            return daoAuthenticationProvider;
        }

    }


