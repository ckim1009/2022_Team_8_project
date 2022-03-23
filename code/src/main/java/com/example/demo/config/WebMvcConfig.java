package com.example.demo.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry)
	{
		MustacheViewResolver resolver = new MustacheViewResolver();
		resolver.setCharset("UTF-8");
		resolver.setContentType("text/html; charset=UTF-8");//던지는 데이터는 html파일 UTF-8
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");//.html파일 만들어도 mustache가 인식
		
		registry.viewResolver(resolver);//mustache 재설정
	}
	

}

