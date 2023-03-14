package io.adarsh.springdatajpaexp.service;

import io.adarsh.springdatajpaexp.model.Employee;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

//@Configuration
public class MultipleBeanForAClass implements ApplicationContextAware, InitializingBean, WebMvcConfigurer {

//    @Value("${bean.employee.quantity}")
    private int quantity;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        for (int i=0; i<quantity; i++)
        beanFactory.registerSingleton("employeeService" + i, new Employee()); // Employee class has to be a normal class

        applicationContext.getBeansOfType(Employee.class);
        assert (applicationContext.getBeansOfType(Employee.class).size() == quantity);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // run this method when all the properties of the bean gets set
        // useful fo removing circular dependency
        // because we can set this class(circular) side of dependency without autowired
        // circB = context.getBean(CircularDependencyB.class); // when other class gets initialized successfully we get the context
    }
}
