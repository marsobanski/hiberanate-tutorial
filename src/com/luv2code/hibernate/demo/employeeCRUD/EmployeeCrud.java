package com.luv2code.hibernate.demo.employeeCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

class EmployeeCrud {

    private EmployeeCrud employeeCrud = null;
    private SessionFactory sessionFactory;
    private Session session;

    public EmployeeCrud getInstance() {
        if (employeeCrud == null) {
            employeeCrud = new EmployeeCrud();
        }
        return employeeCrud;
    }

    private void startTransaction() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        session = sessionFactory
                .getCurrentSession();
        session.beginTransaction();
    }

    private void endTransaction() {
        session.getTransaction().commit();
    }

    public void createEmployee(String firstName, String lastName, String company) {
        try {
            startTransaction();
            Employee employee = new Employee(firstName, lastName, company);
            System.out.println("creating new employee: " + employee);
            session.save(employee);
            System.out.println("Done");
            endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public void readEmployeeById(int id) {
        try {
            startTransaction();
            System.out.println("reading employee by id: " + id);
            Employee employee = getEmployeeById(id);
            System.out.println("read employee: " + employee);
            endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public void readEmployeeByCompany(String company) {
        try {
            startTransaction();
            System.out.println("reading employee by company: " + company);
            List<Employee> employees = getEmployeesByCompany(company);
            System.out.println("got employees");
            employees.forEach(System.out::println);
            endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public void updateEmployeeFirstNameById(int id, String firstName) {
        try {
            startTransaction();
            System.out.println("updating employee by id: " + id + " with firstName: " + firstName);
            Employee employee = getEmployeeById(id);
            employee.setFirstName(firstName);
            session.update(employee);
            endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public void deleteEmployeeById(int id) {
        try {
            startTransaction();
            System.out.println("deleting employee by id: " + id);
            Employee employee = getEmployeeById(id);
            session.delete(employee);
            System.out.println("employee deleted by id: " + id);
            endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    private Employee getEmployeeById(int id) {
        return session.get(Employee.class, id);
    }

    private List<Employee> getEmployeesByCompany(String company) {
        return session.createQuery("from Employee e where e.company like " + "'" + company + "'").getResultList();
    }


}
