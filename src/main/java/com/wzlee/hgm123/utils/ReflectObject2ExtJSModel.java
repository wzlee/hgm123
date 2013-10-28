
package com.wzlee.hgm123.utils;

import java.io.IOException;  
import java.lang.reflect.Field;  
  
import org.codehaus.jackson.annotate.JsonIgnoreProperties;  

import com.wzlee.hgm123.domain.IpData;
  
/** 
 * 根据给出的类映射出前端的ExtJS Model. 
 *  
 * @author <a href="mailto:ge.bugman@gmail.com">葛昊</a> 
 * @since Spring 3.1 Hibernate 4.0 and jdk 1.7 
 * @version %I% %G% 
 */  
public class ReflectObject2ExtJSModel {  
  
    public static String createModel(Class<?> clazz) throws IOException {  
        String[] filters = null;  
        // 判断该类中是否包含懒加载对象，如果包含懒加载对象，则排除在外。  
        if (clazz.isAnnotationPresent(JsonIgnoreProperties.class)) {  
            filters = clazz.getAnnotation(JsonIgnoreProperties.class).value();  
        }  
        Field[] fields = clazz.getDeclaredFields();  
  
        // 这里将里面的包名改成你所需的，或者改用传参的方式也是一个不错的方法！  
        String sb = "Ext.define('hgm.model." + clazz.getSimpleName()  
                + "', {\r\n\textend: 'Ext.data.Model',\r\n\tfields:[";  
  
        fieldFor: for (Field field : fields) {  
            if (filters != null) {  
                for (String f : filters) {  
                    if (f.equals(field.getName())) {  
                        continue fieldFor;  
                    }  
                }  
            }  
            sb += "'" + field.getName() + "',";
        }  
        sb = sb.substring(0, sb.length() - 1);  
        sb += "]\r\n});";  
        // 在这里可以输出为文件，也可以返回一个字符串.  
        return sb.toString();  
    }  
  
    public static void main(String[] args) {  
        try {  
            String model = ReflectObject2ExtJSModel.createModel(IpData.class);  
            System.out.println(model);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
