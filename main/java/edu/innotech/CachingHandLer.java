package edu.innotech;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class CachingHandLer implements InvocationHandler {


    private Object objectIncome;

    private Boolean isCached ;

    private Object resCache;

    public static Boolean isCachedForTest;



    public CachingHandLer(Object objectIncome) {

        this.objectIncome = objectIncome;
        this.isCached = false;
        isCachedForTest = false;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method methodClass =this.objectIncome.getClass().getMethod(method.getName(), method.getParameterTypes());




        if (methodClass.isAnnotationPresent(Mutator.class)){
            this.isCached = false;
            isCachedForTest =  this.isCached;
        }

        if (methodClass.isAnnotationPresent(Cache.class)){
            if (this.isCached){
                return resCache;
            }
        }


        Object res = method.invoke(this.objectIncome, args);
        if (methodClass.isAnnotationPresent(Cache.class)){
            if (!this.isCached){
                this.isCached = true;
                isCachedForTest =  this.isCached;
                resCache = res;
            }
        }
        return res;
    }
}
