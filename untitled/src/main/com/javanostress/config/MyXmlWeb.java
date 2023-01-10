package com.javanostress.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Данный класс заменяет WEB.xml

public class MyXmlWeb extends AbstractAnnotationConfigDispatcherServletInitializer {

    // этот метод не используем
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Сюда поставляем наш класс Конфигурацию (с настройкаим бинов)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    // Перенпраляем все запросоы на ДеспетчерСервлет ()
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
