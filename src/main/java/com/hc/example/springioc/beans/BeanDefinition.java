package com.hc.example.springioc.beans;


/**
 * 封装装bean信息的，主要包含id（即bean对象的名称）、class（需要交由spring管理的类的全类名）及子标签property数据
 * <p>
 * 为了简单实现，直接定义为类
 *
 * @Author hc
 */
public class BeanDefinition {

    private String id;                              // bean唯一id
    private String className;                       // bean全类名
    private MutablePropertyValues propertyValues;   // bean的propertyValue属性

    public BeanDefinition() {
        propertyValues = new MutablePropertyValues();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
