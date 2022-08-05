package org.example;

import com.alibaba.fastjson.JSON;
import org.example.bean.Model;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Model model = new Model();
        model.setSuccess(true);
        System.out.println(JSON.toJSON(model));


    }
}
