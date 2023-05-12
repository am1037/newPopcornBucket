package com.java4.popcorn.cgv;

public class Main {
    public static void main(String[] args) {
        CGV cgv = new CGV();
        cgv.count("0004", 20230513, 20230519).forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
