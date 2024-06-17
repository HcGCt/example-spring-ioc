package com.hc.example.springioc.context.support;

import com.hc.example.springioc.beans.BeanDefinition;
import com.hc.example.springioc.beans.factory.support.BeanDefinitionReader;
import com.hc.example.springioc.beans.factory.support.BeanDefinitionRegistry;
import com.hc.example.springioc.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ApplicationContext子实现类
 * @Author hc
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected BeanDefinitionReader beanDefinitionReader;
    // 单例池,spring源码中在DefaultSingletonBeanRegistry中三级缓存
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 存储配置文件的路径
    protected String configLocation;

    public void refresh() throws IllegalStateException, Exception {
        // 加载BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(configLocation);
        // 初始化bean
        finishBeanInitialization();
    }

    //bean的初始化
    private void finishBeanInitialization() throws Exception {
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        String[] beanNames = registry.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            // 调用getBean()方法使用到了模板方法模式
            getBean(beanName);
        }
    }
}
