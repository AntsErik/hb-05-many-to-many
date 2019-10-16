package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.*;

public class DeleteBilboStudentDemo {

    public static void main( String[] args ){

        //		//Adding standard log4j.properties
        //		BasicConfigurator.configure();

        // create session factory
        SessionFactory factory = new Configuration()
            .configure( "hibernate.cfg.xml" )
            .addAnnotatedClass( Instructor.class )
            .addAnnotatedClass( InstructorDetail.class )
            .addAnnotatedClass( Course.class )
            .addAnnotatedClass( Review.class )
            .addAnnotatedClass( Student.class )
            .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            //start a transaction
            session.beginTransaction();

            //get the student "Bilbo" from DB
            int bilboId = 1;
            Student tempStudent = session.get( Student.class, bilboId );

            System.out.println( "\nLoading student: " + tempStudent );
            System.out.println( "\nLoaded Student current courses: " + tempStudent.getCourses() );

            //delete student
            System.out.println( "\nDeleting student: " + tempStudent );
            session.delete( tempStudent );

            //commit transaction
            session.getTransaction().commit();

            System.out.println( "\n\nDone!" );
        }
        finally {

            //add cleanup mode
            session.close();

            factory.close();
        }
    }
}
