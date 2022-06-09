package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //READING CODE
            int id = 5;
            System.out.println("saved student with id " + id);

            //get new session, start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student by id
            System.out.println("retrieving student by id " + id);
            Student student = session.get(Student.class, id);
            System.out.println("got student " + student);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
