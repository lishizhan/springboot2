package org.example.lambda;

import java.util.function.IntBinaryOperator;

/**
 * @Author : Alishiz
 * @Date : 2022/8/2/0002
 * @email : 1575234570@qq.com
 * @Description :
 */
public class LambdaTest {
    public static void main(String[] args) {
        int i = calculateNum((left, right) -> 0);
    }

    public static int calculateNum(IntBinaryOperator operator){
        int a = 20;
        int b = 20;
        return operator.applyAsInt(a,b);
    }
}
