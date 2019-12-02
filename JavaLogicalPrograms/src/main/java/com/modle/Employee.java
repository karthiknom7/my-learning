package com.modle;

import java.math.BigDecimal;

public class Employee {
    private Integer id;
    private String name;
    private BigDecimal salary;


    public Employee(Integer id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
