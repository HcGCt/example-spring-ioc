package com.hc.example.springioc.beans.factory.support;

import com.hc.example.springioc.beans.BeanDefinition;

/**
 * 定义了注册表的相关操作
 * <p>
 * 注册表即用于管理BeanDefinition信息
 *
 * @Author hc
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition对象到注册表中
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 从注册表中删除指定名称的BeanDefinition对象
     */
    void removeBeanDefinition(String beanName) throws Exception;

    /**
     * 根据名称从注册表中获取BeanDefinition对象
     */
    BeanDefinition getBeanDefinition(String beanName) throws Exception;

    /**
     * 判断是否包含beanName的BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取注册表中BeanDefinition个数
     */
    int getBeanDefinitionCount();

    /**
     * BeanDefinition名数组
     */
    String[] getBeanDefinitionNames();
}
