package com.javanostress.config;

// конфигурационный класс

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Set;
// Файлс с настрофками для Спринг MVC -> РАВНОСИЛЬНА КОНФИГУРАЦИИ -> applicationContextMVC.xml


// Метод configureViewResolvers реализуется тогда, когда мы хотим настроить  Спринг MVC ддя себя ->
// -> т.к. вместо стандартного шаблонизатора, мы используем шаблрнизатор  Thymeleaf, поэтому реализуем implements WebMvcConfigurer ->
// -> и метод   public void configureViewResolvers(ViewResolverRegistry registry)



@Configuration
@ComponentScan("com.javanostress")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig ( ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver () {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine () {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolvers((Set<ITemplateResolver>) templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // В этом методе задаем шаблонизатор  Thymeleaf
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        registry.viewResolver(thymeleafViewResolver);
    }
}
