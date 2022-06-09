package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //create 3 students object
            System.out.println("creating 3 students");
            //żeby weszły polskie znaki trzeba zmienić collation (ALTER TABLE student CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;)
            Student student = new Student("Marian", "Sowiński", "miarian@marian.pl", DateUtils.parseDate("12/03/2011"));
            Student student2 = new Student("Damian", "Laskowski", "damian@marian.pl", DateUtils.parseDate("05/11/1995"));
            Student student3 = new Student("Herman", "Goliwąs", "herman@marian.pl", DateUtils.parseDate("15/01/2003"));

            //start transaction
            session.beginTransaction();

            //save student object
            System.out.println("saving student");
            session.save(student);
            session.save(student2);
            session.save(student3);

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
