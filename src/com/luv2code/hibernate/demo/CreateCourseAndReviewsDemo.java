package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndReviewsDemo {

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
			
			//create some courses
			Course tempCourse1 = new Course("Fighting - The Ultimate Warrior");
			Course tempCourse2 = new Course("High-tech knowledge");
			
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			//create the students
			Student tempStudent1 = new Student("Bilbo", "Baggins", "hobbit@lotr.com");
			Student tempStudent2 = new Student("Frodo", "Baggins", "ringbearer@lotr.com");
			
			//add students to the course
			tempCourse1.addStudent(tempStudent1);
			tempCourse1.addStudent(tempStudent2);
			tempCourse2.addStudent(tempStudent1);
			
			//save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\n\nSaved students: " + tempCourse1.getStudents());
			System.out.println("\n\nSaved students: " + tempCourse2.getStudents());
			
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
