package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //session.createQuery("delete Student s where s.id = 2").executeUpdate();  - inny sposób, wszystko można na te dwa;

            Student student = session.get(Student.class, 2);
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
