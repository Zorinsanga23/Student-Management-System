package project;

import java.util.*;

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String toString() { return "ID: " + id + " | Name: " + name; }
}

class Course {
    private String code, title;
    private List<String> subjects = new ArrayList<>();

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public List<String> getSubjects() { return subjects; }
    public void addSubject(String subject) { subjects.add(subject); }

    public String toString() {
        return code + " - " + title + " | Subjects: " + (subjects.isEmpty() ? "None" : String.join(", ", subjects));
    }
}

class Mark {
    private Student student;
    private Course course;
    private String subject;
    private int score;

    public Mark(Student student, Course course, String subject, int score) {
        this.student = student;
        this.course = course;
        this.subject = subject;
        this.score = score;
    }

    public String getGrade() {
        return score >= 90 ? "A+" : score >= 80 ? "A" : score >= 70 ? "B" : score >= 60 ? "C" : score >= 50 ? "D" : "F";
    }

    public String toString() {
        return student.getName() + " scored " + score + " (" + getGrade() + ") in " + subject + " [" + course.getCode() + "]";
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n1. Students \n2. Courses \n3. Marks \n4. Exit");
            switch (inputInt("Choose option: ")) {
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> manageMarks();
                case 4 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void manageStudents() {
        System.out.println("1. Add \n2. View");
        if (inputInt("Option: ") == 1) {
            int id = inputInt("ID: ");
            if (findStudent(id) != null) { System.out.println("Exists."); return; }
            System.out.print("Name: ");
            students.add(new Student(id, scanner.nextLine()));
        } else students.forEach(System.out::println);
    }

    private void manageCourses() {
        System.out.println("1. Add Course \n2. View \n3. Add Subject");
        int ch = inputInt("Option: ");
        if (ch == 1) {
            System.out.print("Code: "); String code = scanner.nextLine();
            if (findCourse(code) != null) { System.out.println("Exists."); return; }
            System.out.print("Title: ");
            courses.add(new Course(code, scanner.nextLine()));
        } else if (ch == 2) courses.forEach(System.out::println);
        else {
            System.out.print("Course Code: "); Course c = findCourse(scanner.nextLine());
            if (c == null) { System.out.println("Not found."); return; }
            System.out.print("Subject: "); c.addSubject(scanner.nextLine());
        }
    }

    private void manageMarks() {
        System.out.println("1. Assign \n2. View \n3. Search by Student");
        int ch = inputInt("Option: ");
        if (ch == 1) {
            Student s = findStudent(inputInt("Student ID: "));
            if (s == null) { System.out.println("Not found."); return; }
            Course c = findCourse(inputStr("Course Code: "));
            if (c == null || c.getSubjects().isEmpty()) { System.out.println("Invalid Course/No Subjects."); return; }

            List<String> subs = c.getSubjects();
            for (int i = 0; i < subs.size(); i++) System.out.println((i+1) + ". " + subs.get(i));
            int idx = inputInt("Subject No: ") - 1;
            if (idx < 0 || idx >= subs.size()) return;
            int score = inputInt("Score: ");
            marks.add(new Mark(s, c, subs.get(idx), score));
        } else if (ch == 2) marks.forEach(System.out::println);
        else {
            int id = inputInt("Student ID: ");
            marks.stream().filter(m -> m.toString().contains("ID: " + id)).forEach(System.out::println);
        }
    }

    private int inputInt(String msg) {
        System.out.print(msg);
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }

    private String inputStr(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private Student findStudent(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    private Course findCourse(String code) {
        return courses.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }
}

public class Manager {
    public static void main(String[] args) {
        new StudentManager().start();
    }
}
