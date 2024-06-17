package com.hc.example.springioc.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 存储和管理多个PropertyValue对象
 *
 * @Author hc
 */
public class MutablePropertyValues implements Iterable<PropertyValue> {

    // 存储PropertyValue对象
    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        propertyValueList = new ArrayList<>();
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        if (propertyValueList == null) {
            this.propertyValueList = new ArrayList<>();
        } else {
            this.propertyValueList = propertyValueList;
        }
    }

    /**
     * 获取PropertyValue数组
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 获取PropertyValue
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    /**
     * 判断集合是否为空
     */
    public boolean isEmpty() {
        return propertyValueList.isEmpty();
    }

    /**
     * 添加ProperValue
     */
    public MutablePropertyValues addPropertyValue(PropertyValue propertyValue) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue pv = propertyValueList.get(i);
            if (pv.getName().equals(propertyValue.getName())) {
                propertyValueList.set(i, propertyValue);
                return this;
            }
        }
        propertyValueList.add(propertyValue);
        return this;
    }

    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValueList.iterator();
    }
}
