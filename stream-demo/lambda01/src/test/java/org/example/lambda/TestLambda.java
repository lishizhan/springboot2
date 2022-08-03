package org.example.lambda;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/3 9:53
 */
public class TestLambda {
    @Test
    public void test01(){
        //printNum(value -> value>5);
        Integer integer = typeConver(s -> Integer.parseInt(s));
        System.out.println("integer = " + integer);

    }

    public static void printNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9};
        for (int i : arr) {
            if (predicate.test(i)){
                System.out.println("i = " + i);
            }
        }
    }

    public static <R> R typeConver(Function<String,R> function){
        String str = "1234";
        R apply = function.apply(str);
        return apply;
    }
    public static void forArr(IntConsumer intConsumer){
        int[] arr = {1,2,3,4,5,6,7};
        for (int i : arr) {
            intConsumer.accept(i);
        }
    }
    @Test
    public void testForArr(){
        forArr(value -> System.out.println("value = " + value));
    }



}
