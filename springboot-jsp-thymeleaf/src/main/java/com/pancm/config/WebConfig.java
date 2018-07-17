package com.pancm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * 
* @Title: WebConfig
* @Description: 
* JSP和Thymeleaf模板的设置
* @Version:1.0.0  
* @author pancm
* @date 2018年7月16日
 */
@Configuration//用来定义 DispatcherServlet 应用上下文中的 bean
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {
	
	   @Bean
       public ViewResolver viewResolver() {
           InternalResourceViewResolver resolver = new InternalResourceViewResolver();
           resolver.setPrefix("/WEB-INF/");
           resolver.setSuffix(".jsp");
           resolver.setViewNames("jsp/*");
           resolver.setOrder(2);
           return resolver;
       }

       @Bean
       public ITemplateResolver templateResolver() {
           SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
           templateResolver.setTemplateMode("HTML5");
           templateResolver.setPrefix("/WEB-INF/");
           templateResolver.setSuffix(".html");
           templateResolver.setCharacterEncoding("utf-8");
           templateResolver.setCacheable(false);
           return templateResolver;
       }

       @Bean
       public SpringTemplateEngine templateEngine() {
           SpringTemplateEngine templateEngine = new SpringTemplateEngine();
           templateEngine.setTemplateResolver(templateResolver());
           return templateEngine;
       }

       @Bean
       public ThymeleafViewResolver viewResolverThymeLeaf() {
           ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
           viewResolver.setTemplateEngine(templateEngine());
           viewResolver.setCharacterEncoding("utf-8");
           viewResolver.setViewNames(new String[]{"thymeleaf/*"});
           viewResolver.setOrder(1);
           return viewResolver;
       }

       @Override
       public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
           configurer.enable();
       }

       @Override
       public void addResourceHandlers(ResourceHandlerRegistry registry) {
           super.addResourceHandlers(registry);
       }
}


