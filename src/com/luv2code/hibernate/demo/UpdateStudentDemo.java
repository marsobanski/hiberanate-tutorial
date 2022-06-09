package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            int stgudentId = 4;
            System.out.println("get student with id = " + stgudentId);

            Student student = session.get(Student.class, stgudentId);

            System.out.println("updating student...");
            student.setEmail("email@test.com");
            session.getTransaction().commit();

            session = factory.getCurrentSession();

            session.beginTransaction();

            System.out.println("Done!");

            //NEW CODE
            System.out.println("Updating all students emails");
            session.createQuery("from Student").getResultList().stream().forEach(s -> ((Student) s).setEmail("email@test.pl"));
            session.getTransaction().commit();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
