package com.example.demo;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class FirstTest {
    @Test
    public void testcase(){
        Calculation calculation = new Calculation();
       Assertions.assertEquals(8,calculation.addition(3, 5)); ;
    }



}
