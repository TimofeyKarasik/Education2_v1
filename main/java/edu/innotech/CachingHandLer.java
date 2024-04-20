package edu.innotech;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class CachingHandLer implements InvocationHandler {


    private Object objectIncome;

    private Map<Method, Object> cacheObj = new HashMap<>();




    public CachingHandLer(Object objectIncome) {

        this.objectIncome = objectIncome;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method methodClass =this.objectIncome.getClass().getMethod(method.getName(), method.getParameterTypes());




        if (methodClass.isAnnotationPresent(Mutator.class)){
            cacheObj.clear();
        }

        if (methodClass.isAnnotationPresent(Cache.class)){
            if (cacheObj.containsKey(methodClass)){
                return cacheObj.get(methodClass);
            }
        }


        Object res = method.invoke(this.objectIncome, args);
        if (methodClass.isAnnotationPresent(Cache.class)){
            cacheObj.put(methodClass,res);
        }
        return res;
    }
}
