package net.lamad.spring.dvalue.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TargetMethod {
    private final Object bean;
    private final Method method;
    private final DValue dValue;

    TargetMethod(Object bean, Method method, DValue dValue) {
        this.bean = bean;
        this.method = method;
        this.dValue = dValue;
    }

    public Class getParameterType() {
        return method.getParameterTypes()[0];
    }

    public void call(Object value) throws InvocationTargetException, IllegalAccessException {
        method.invoke(bean, value);
    }

    public DValue getDValueAnnotation() {
        return dValue;
    }

    public String getAsFilePath() {
        String path = dValue.path().replace('.', '/');
        if (path.charAt(0) != '/') {
            path = '/' + path;
        }
        return path;
    }

    public String getAsPropertyPath() {
        String path = dValue.path().replace('/', '.');
        if (path.charAt(0) == '.') {
            path = path.substring(1);
        }
        return path;
    }
}
