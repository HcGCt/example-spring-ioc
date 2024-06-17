package com.hc.example.springioc.beans;

/**
 * 用于封装bean的属性，体现到上面的配置文件就是封装bean标签的子标签property标签数据。
 *
 * @Author hc
 */
public class PropertyValue {

    private String name;        // bean名
    private String ref;
    private String value;       // 给基本数据类型及String类型数据赋的值

    public PropertyValue() {
    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
