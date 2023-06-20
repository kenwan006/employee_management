package org.example.pojo;

import lombok.Data;

@Data
public class Employee {
    private long id;
    private String number;
    private String name;
    private int age;
    private String position;
    private String department;
    private  int yoe;
    private int salary;
    private int pto;
}
