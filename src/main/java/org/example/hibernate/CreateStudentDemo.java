package org.example.hibernate;

import org.example.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateStudentDemo {
    public static void main(String[] args)
    {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //create a student object
            System.out.println("Creating new student object...");
            //Student tempStudent = new Student("Maryam","Gadji","merr@gmail.com");

            Customer tempStudent = new Customer();

            tempStudent= new Customer("Maryam","Gadji","merr@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");

                session.save(tempStudent);


            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally
        {
            factory.close();
        }
    }
}
