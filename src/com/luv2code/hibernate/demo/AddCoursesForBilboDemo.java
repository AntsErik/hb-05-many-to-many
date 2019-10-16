package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.*;

public class AddCoursesForBilboDemo {

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

            //Create some more courses
            Course tempCourse1 = new Course( "Dragons - how to train your dragon!" );
            Course tempCourse2 = new Course( "Dwarves - Gold or Gold? Nothing else matters to dwarwes!" );

            //Add student to those courses
            tempCourse1.addStudent( tempStudent );
            tempCourse2.addStudent( tempStudent );

            //Save the created courses
            System.out.println( "\nSaving the courses..." );

            session.save( tempCourse1 );
            session.save( tempCourse2 );

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
