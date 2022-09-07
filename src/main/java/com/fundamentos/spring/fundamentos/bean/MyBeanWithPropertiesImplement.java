package com.fundamentos.spring.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties{

    private String name;
    private String lastname;

    public MyBeanWithPropertiesImplement(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String funcion() {
        return name+"-"+lastname;
    }
}
