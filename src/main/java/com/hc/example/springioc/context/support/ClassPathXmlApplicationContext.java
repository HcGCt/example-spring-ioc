package com.hc.example.springioc.context.support;

import com.hc.example.springioc.beans.BeanDefinition;
import com.hc.example.springioc.beans.MutablePropertyValues;
import com.hc.example.springioc.beans.PropertyValue;
import com.hc.example.springioc.beans.factory.support.BeanDefinitionRegistry;
import com.hc.example.springioc.beans.factory.xml.XmlBeanDefinitionReader;
import com.hc.example.springioc.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * 容器实现类子类
 *
 * @Author hc
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        // 构建XmlBeanDefinitionReader对象
        beanDefinitionReader = new XmlBeanDefinitionReader();
        try {
            this.refresh();
        } catch (Exception e) {

        }
    }

    // 根据bean的name属性值获取bean对象
    @Override
    public Object getBean(String name) throws Exception {

        Object obj = singletonObjects.get(name);
        if (obj != null) {
            return obj;
        }

        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        BeanDefinition beanDefinition = registry.getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        // 1.反射获取实例
        String className = beanDefinition.getClassName();
        Class<?> clazz = Class.forName(className);
        Object beanObj = clazz.newInstance();
        // 2.依赖注入
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String propertyName = propertyValue.getName();
            String value = propertyValue.getValue();
            String ref = propertyValue.getRef();
            if (ref != null && !"".equals(ref)) {
                // 2.1获取依赖bean对象
                Object bean = getBean(ref);
                // 2.2 setter注入
                String methodName = StringUtils.getSetterMethodNameByFieldName(propertyName);
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        method.invoke(beanObj, bean);
                    }
                }
            }
            // 2.3 value类型注入
            if (value != null && !"".equals(value)) {
                String methodName = StringUtils.getSetterMethodNameByFieldName(propertyName);
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(beanObj, value);
            }
        }
        singletonObjects.put(name, beanObj);
        return beanObj;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        Object bean = getBean(name);
        if (bean != null) {
            return clazz.cast(bean);
        }
        return null;
    }
}
