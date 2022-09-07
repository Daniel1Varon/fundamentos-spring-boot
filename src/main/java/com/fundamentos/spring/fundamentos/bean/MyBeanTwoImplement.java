package com.fundamentos.spring.fundamentos.bean;

public class MyBeanTwoImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("Hola desde mi implementación propia del bean two");
    }
}
