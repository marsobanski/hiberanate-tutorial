package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class CreateStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //create student object
            System.out.println("creating ne student");
                //żeby weszły polskie znaki trzeba zmienić collation (ALTER TABLE student CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;)
            Student student = new Student("Marian", "Sowiński", "miarian@marian.pl", DateUtils.parseDate("26/05/1993"));

            //start transaction
            session.beginTransaction();
            //save student object
            System.out.println("saving student");
            session.save(student);

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
