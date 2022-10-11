package com.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//사용자 정의 UserDetailsService 사용
		//http.userDetailsService(userDetailsService);
		   
		//http.csrf().disable();   //csrf 비활성화
		//security.formLogin();        //스프링부트 제공 로그인 폼 실행
		http.formLogin().loginPage("/member/login")
		        .defaultSuccessUrl("/", true);
		//접근 권한 없음 페이지 처리
		http.exceptionHandling().accessDeniedPage("/member/accessDenied");  
		//로그아웃 후 로그인 페이지로 이동
		http.logout().logoutUrl("/member/logout")
					 .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
					 .invalidateHttpSession(true)
					 .logoutSuccessUrl("/");
		
		/*http.authorizeRequests()
        .antMatchers("/", "/member/**").permitAll() //인증되지 않은 모든 사용자 접근
        .antMatchers("/board/**").authenticated()  //로그인한 사용자만 접근
        .antMatchers("/admin/**").hasRole("ADMIN");//ADMIN 권한을 가진 사용자만 접근*/
		
		//인증과 권한 설정
		http.authorizeRequests()
			.mvcMatchers("/", "/member/**").permitAll()
			.mvcMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
	//비밀번호 암호화 : PasswordEncoder 객체를 리턴하는 passwordEncoder()
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
