package com.luv2code.hibernate.demo.employeeCRUD;

public class EmployeeMain {
    public static void main(String[] args) {
        EmployeeCrud employeeCrud = new EmployeeCrud();
        employeeCrud.readEmployeeById(2);
    }
}
