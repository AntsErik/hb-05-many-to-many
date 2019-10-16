package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class GetCoursesForSingleStudentDemo {

	public static void main(String [] args){
		
//		//Adding standard log4j.properties
//		BasicConfigurator.configure();
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();

			//get the student from DB
			int studentId = 2;
			Student tempStudent = session.get(Student.class,  studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("\nLoaded students courses: " + tempStudent.getCourses());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("\n\nDone!");
		}
		finally {
			
			//add cleanup mode
			session.close();
			
			factory.close();
		}
	}
}
