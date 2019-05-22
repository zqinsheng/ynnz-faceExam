package org.zhouqinsheng.faceExam.apiTools;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String,Object> getAllProperties(Object target) {

        Map<String, Object> map = new HashMap<>();

        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);

        for (PropertyDescriptor targetPd : targetPds) {

            if (targetPd.getReadMethod()!=null) {

                try {

                    Method readMethod = targetPd.getReadMethod();

                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {

                        readMethod.setAccessible(true);

                    }

                    Object value = readMethod.invoke(target);

                    if (value != null) {

                        map.put(targetPd.getName(), value);

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

        return map;

    }

}
