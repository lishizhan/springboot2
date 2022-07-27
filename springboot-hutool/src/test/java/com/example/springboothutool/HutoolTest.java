package com.example.springboothutool;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

public class HutoolTest {


    /**
     * Convert类型转换
     * */
    @Test
    public void ConvertTest(){
        //String--->Long
        Long aLong = Convert.toLong("123",2000L);
        System.out.println("aLong = " + aLong);
        //数组转为字符串
        int[] arrInt = {1,2,3,8,23,4};
        String str = Convert.toStr(arrInt);
        System.out.println("str = " + str);
    }

}
