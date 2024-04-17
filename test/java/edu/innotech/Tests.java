package edu.innotech;

import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    public void testCache() {
        Fractionable f = new Fraction(1,2);


        f= Utils.cache(f);

        System.out.println(f.doubleValue());
        if (!CachingHandLer.isCachedForTest) {
            throw
                    new IllegalArgumentException("Провал теста: Кэширование не произошло");
        }

        System.out.println(f.doubleValue());
        System.out.println(f.doubleValue());

        f.setDenum(4);

        if (CachingHandLer.isCachedForTest) {
            throw new IllegalArgumentException("Провал теста: Кэш не сбросился при setDenum");
        }

        System.out.println(f.doubleValue());

        if (!CachingHandLer.isCachedForTest) {
            throw new IllegalArgumentException("Провал теста: Кэширование не произошло");
        }


        f.setNum(9);

        if (CachingHandLer.isCachedForTest) {
            throw new IllegalArgumentException("Провал теста: Кэш не сбросился при setNum");
        }

        System.out.println(f.doubleValue());
        if (!CachingHandLer.isCachedForTest) {
            throw new IllegalArgumentException("Провал теста: Кэширование не произошло");
        }

        System.out.println("Успех теста");

        }

}