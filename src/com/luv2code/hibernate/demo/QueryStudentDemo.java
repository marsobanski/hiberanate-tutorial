package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

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

            //query students
            List<Student> students = session.createQuery("from Student").getResultList();

            //query student lastName contains "k"
            List<Student> studentsWithK = session.createQuery("from Student s where s.lastName like '%k%'").getResultList();

            //display students
            displayStudents(students);
            displayStudents(studentsWithK);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

            //w src/resources/logback.xml jest konfiguracja dzięki której logback loguje podstawowe dane w konsoli, taki dodatek

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        students.stream().forEach(System.out::println);
        System.out.println("");
    }
}
