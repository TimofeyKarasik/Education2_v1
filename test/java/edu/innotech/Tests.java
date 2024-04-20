package edu.innotech;

import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    public void testCache() {

        Fraction f1 = new Fraction(1,2);
        Fractionable f2;

        f2= Utils.cache(f1);


        if (f1.doubleValue()==f2.doubleValue()&&f1.toString().equals(f2.toString())){
            System.out.println("Успех значение закэшировалось");
        }

        f1.setDenum(4);

        if (f1.doubleValue()!=f2.doubleValue()&&!f1.toString().equals(f2.toString())){
            System.out.println("Успех значение осталось закэшированно после изменения исходного обьекта");
        }

        f2.setDenum(4);

        if (f1.doubleValue()==f2.doubleValue()&&f1.toString().equals(f2.toString())){
            System.out.println("Успех значение кэша переинициализировались");
        }

        System.out.println("Успех теста");

        }

}