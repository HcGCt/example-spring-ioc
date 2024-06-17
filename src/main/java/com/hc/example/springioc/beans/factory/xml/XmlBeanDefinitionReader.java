package com.hc.example.springioc.beans.factory.xml;

import com.hc.example.springioc.beans.BeanDefinition;
import com.hc.example.springioc.beans.MutablePropertyValues;
import com.hc.example.springioc.beans.PropertyValue;
import com.hc.example.springioc.beans.factory.support.BeanDefinitionReader;
import com.hc.example.springioc.beans.factory.support.BeanDefinitionRegistry;
import com.hc.example.springioc.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * xml配置文件解析
 *
 * @Author hc
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader() {
        this.registry = new SimpleBeanDefinitionRegistry();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public void loadBeanDefinitions(String configLocation) throws Exception {
        // xml解析
        SAXReader reader = new SAXReader();
        // 通过类加载器获取classpath下的配置文件
        InputStream is = XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configLocation);
        Document document = reader.read(is);

        parseBean(document.getRootElement());
    }

    private void parseBean(Element root) {
        List<Element> elements = root.elements();
        for (Element element : elements) {
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);
            List<Element> list = element.elements("property");
            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
            for (Element element1 : list) {
                String name = element1.attributeValue("name");
                String ref = element1.attributeValue("ref");
                String value = element1.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue(name, ref, value);
                mutablePropertyValues.addPropertyValue(propertyValue);
            }
            beanDefinition.setPropertyValues(mutablePropertyValues);

            registry.registerBeanDefinition(id, beanDefinition);
        }
    }
}
