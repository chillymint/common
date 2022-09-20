package com.nb;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @author yunfu.ye
 * @date 2022/9/8 15:10
 * @desciption:
 * lambda省略规则:
 * 1.参数类型可以省略
 * 2.方法体只有一句代码时大括号return和唯一一句代码的分号可以省略
 * 3.方法只有一个参数时小括号可以省略
 */
public class LambdaDemo01 {
    public static void main(String[] args) {
        // //练习1
        // int i = calculateNum((int left, int right) -> {
        //     return left + right;
        // });
        // System.out.println(i);
        // -----------------------------------------------------------------
         //练习2 匿名内部类写法
        // printNum(new IntPredicate() {
        //     @Override
        //     public boolean test(int value) {
        //         return value%2==0;
        //     }
        // });

        // printNum((int value) -> {
        //     return value%2==0;
        // });
        // -----------------------------------------------------------------
        //练习3 匿名内部类调用
        // Integer integer = typeConver(new Function<String, Integer>() {
        //     @Override
        //     public Integer apply(String s) {
        //         return Integer.valueOf(s);
        //     }
        // });

        // Integer integer = typeConver((String s) -> {
        //     return Integer.valueOf(s);
        // });
        // System.out.println(integer);
        // -----------------------------------------------------------------
        //练习4
        // foreachArr(new IntConsumer() {
        //     @Override
        //     public void accept(int value) {
        //         System.out.println(value);
        //     }
        // });

        foreachArr((int value)-> {
             System.out.println(value);
        });

    }
    public static void fun1(){
        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println(1);
        //     }
        // }).start();
        //lambda表达式写法
        new Thread(() -> {
            System.out.println(1);
        }).start();
    }

    // 参数是一个函数式接口,练1
    public static int calculateNum(IntBinaryOperator intBinaryOperator){
        int a=10;
        int b=20;
        return intBinaryOperator.applyAsInt(a,b);

    }
    // 练习2
    public static void printNum(IntPredicate intPredicate){
        int [] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if(intPredicate.test(i)){
                System.out.println(i);
            }
        }
    }
    // 练习3 <R> R 是方法泛型
    public static <R> R typeConver(Function<String,R> function){
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }
}
