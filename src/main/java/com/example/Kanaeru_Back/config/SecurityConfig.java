package com.example.Kanaeru_Back.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
	                      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						// OPTIONS リクエストは全て許可（CORS プリフライト）
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						
						// 認証不要のエンドポイント
						.requestMatchers("/api/auth/login").permitAll()
						.requestMatchers("/api/auth/registration/**").permitAll()
						.requestMatchers("/api/auth/forgotPassword").permitAll()
						.requestMatchers("/api/auth/resetPassword").permitAll()
						.requestMatchers("/api/webhooks/stripe").permitAll()
						.requestMatchers("/api/stripe/subscription/create").permitAll()
						.requestMatchers("/api/slack/events").permitAll()
						.requestMatchers("/api/slack/oauth/callback").permitAll()
						.requestMatchers("/actuator/**").permitAll()
						.requestMatchers("/test/batch/**").permitAll() 
												
						// その他全てのエンドポイントは認証が必要
						.anyRequest().authenticated()
				)
				// 認証エラー時のハンドラー
				.exceptionHandling(exception -> exception
						.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				)
				// JWT認証フィルターをUsernamePasswordAuthenticationFilterの前に追加
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		// デバッグ用ログ
		System.out.println("=== CORS Configuration Loading ===");
		
		// allowCredentials=trueの場合はsetAllowedOriginPatternsを使用する必要がある
		configuration.setAllowedOriginPatterns(Arrays.asList(
				"https://kanaeru.etomoji.co.jp",
				"http://kanaeru.etomoji.co.jp",
				"https://staging.kanaeru.etomoji.co.jp",
				"http://staging.kanaeru.etomoji.co.jp",
				"http://13.114.155.51:5180",
				"http://35.74.40.37:5180",
				"http://localhost:5180",
				"http://192.168.1.9:5180"
		));
		
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);
		
		System.out.println("Allowed Origin Patterns: " + configuration.getAllowedOriginPatterns());
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		// Cookie内の日本語文字を含む非ASCII文字を許可
		firewall.setAllowUrlEncodedPercent(true);
		firewall.setAllowUrlEncodedSlash(true);
		firewall.setAllowSemicolon(true);
		firewall.setAllowBackSlash(true);
		firewall.setAllowUrlEncodedPeriod(true);
		// Cookie値の検証を緩和
		firewall.setAllowedHeaderNames(header -> true);
		firewall.setAllowedHeaderValues(header -> true);
		return firewall;
	}

	// CorsFilter のBean登録は削除（不要）
}