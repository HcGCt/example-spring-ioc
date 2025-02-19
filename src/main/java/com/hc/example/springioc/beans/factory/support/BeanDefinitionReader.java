package com.hc.example.springioc.beans.factory.support;

/**
 * 解析配置文件并在注册表中注册bean的信息
 * @Author hc
 */
public interface BeanDefinitionReader {

    /**
     * 获取注册表对象
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 加载配置文件并在注册表中进行注册
     */
    void loadBeanDefinitions(String configLocation) throws Exception;
}
