package pj;

import java.util.Scanner;

class Student {
	protected int Roll;
	protected String Name;
	protected String course;
	protected int sem;

	public void student_input(Scanner s) {
		System.out.println("Enter details of student:");
		System.out.print("Roll no: ");
		Roll = s.nextInt();
		s.nextLine();
		System.out.print("Name: ");
		Name = s.nextLine();
		System.out.print("Course: ");
		course = s.nextLine();
		System.out.print("Semester: ");
		sem = s.nextInt();
	}

	public void student_display() {
		System.out.println("------ Student Details ------");
		System.out.println("Roll no   : " + Roll);
		System.out.println("Name      : " + Name);
		System.out.println("Course    : " + course);
		System.out.println("Semester  : " + sem);
	}
}

class Course extends Student {
	protected String courseCode;
	protected String instructor;

	public void course_input(Scanner s) {
		s.nextLine();
		System.out.print("Enter Course Code: ");
		courseCode = s.nextLine();
		System.out.print("Enter Instructor Name: ");
		instructor = s.nextLine();
	}

	public void course_display() {
		System.out.println("Course Code      : " + courseCode);
		System.out.println("Instructor Name  : " + instructor);
	}
}

class Exam extends Course {
	protected int sub1, sub2, sub3, sub4, sub5;
	protected int total;
	protected float per;
	protected String grd;

	public void exam_input(Scanner sc) {
		System.out.println("Enter the marks for 5 subjects (out of 100):");
		System.out.print("Subject 1: ");
		sub1 = sc.nextInt();
		System.out.print("Subject 2: ");
		sub2 = sc.nextInt();
		System.out.print("Subject 3: ");
		sub3 = sc.nextInt();
		System.out.print("Subject 4: ");
		sub4 = sc.nextInt();
		System.out.print("Subject 5: ");
		sub5 = sc.nextInt();
	}

	public void exam_calculate() {
		total = sub1 + sub2 + sub3 + sub4 + sub5;
		per = (float) total / 500 * 100;

		if (per >= 80)
			grd = "A";
		else if (per >= 70)
			grd = "B";
		else if (per >= 60)
			grd = "C";
		else if (per >= 50)
			grd = "D";
		else
			grd = "F";
	}

	public void exam_display() {
		System.out.println("------ Exam Result ------");
		System.out.println("Subject 1 : " + sub1);
		System.out.println("Subject 2 : " + sub2);
		System.out.println("Subject 3 : " + sub3);
		System.out.println("Subject 4 : " + sub4);
		System.out.println("Subject 5 : " + sub5);
		System.out.println("Total Marks : " + total);
		System.out.println("Percentage  : " + per);
		System.out.println("Grade       : " + grd);
	}
}

class Result extends Exam {
	public void display_full_result() {
		student_display();
		course_display();
		exam_display();
	}
}

public class Student_Management_System {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Result r1 = new Result();

		r1.student_input(sc);
		r1.course_input(sc);
		r1.exam_input(sc);
		r1.exam_calculate();
		r1.display_full_result();

		sc.close();
	}
}
