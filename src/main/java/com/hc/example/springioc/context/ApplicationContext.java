package com.hc.example.springioc.context;

import com.hc.example.springioc.beans.factory.BeanFactory;

/**
 * bean容器非延迟加载
 * 核心在于方法refresh(),初始化所有bean
 *
 * @Author hc
 */
public interface ApplicationContext extends BeanFactory {

    /**
     * 立即加载所有bean
     */
    void refresh() throws Exception;
}
