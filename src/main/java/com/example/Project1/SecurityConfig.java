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
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;
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
                    .antMatchers("/allEmployees").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/createEmployee").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager")
                    .antMatchers("/updateEmployee/{employeeId}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager")
                    .antMatchers("/getEmployeeBySearchCriteria/{searchCriteria}/{searchId}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/allTasks").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/deleteTask/{taskId}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager")
                    .antMatchers("/findTaskByDifficultyAndNumberOfEmployees/{difficulty}/{numberOfEmployees}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/findTaskByDifficulty/{difficulty}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/findTaskByNumberOfEmployees/{numberOfEmployees}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/findTaskById/{taskId}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager","employee")
                    .antMatchers("/createTask").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager")
                    .antMatchers("/updateTask/{taskId}").hasAnyRole("admin", "companyManager", "businessUnitManager", "departmentManager","unitManager")
                    .antMatchers("/createCompany").hasAnyRole("admin","companyManager")
                    .antMatchers("/updateCompany/{companyId}").hasAnyRole("admin","companyManager")
                    .antMatchers("/createBusinessUnit").hasAnyRole("admin","companyManager","businessUnitManager")
                    .antMatchers("/updateBusinessUnit/{businessUnitId}").hasAnyRole("admin","companyManager","businessUnitManager")
                    .antMatchers("/createDepartment").hasAnyRole("admin","companyManager","businessUnitManager","departmentManager")
                    .antMatchers("/updateDepartment/{departmentId}").hasAnyRole("admin","companyManager","businessUnitManager","departmentManager")
                    .antMatchers("/createUnit").hasAnyRole("admin","companyManager","businessUnitManager","departmentManager","unitManager")
                    .antMatchers("/updateUnit/{unitId}").hasAnyRole("admin","companyManager","businessUnitManager","departmentManager","unitManager")

                    .anyRequest().fullyAuthenticated()



                    .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                    .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())

                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
            
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

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint(){
            return new CustomAuthenticationEntryPoint();
        }

    }


