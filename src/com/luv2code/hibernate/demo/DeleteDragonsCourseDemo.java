package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.*;

public class DeleteDragonsCourseDemo {

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

            //get the Dragons course from DB
            int dragonId = 14;
            Course tempCourse = session.get( Course.class, dragonId );

            //delete the course
            System.out.println( "\nDeleting course: " + tempCourse );

            session.delete( tempCourse );

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
