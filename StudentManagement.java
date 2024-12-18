import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class StudentManagement {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        String filename = "Student.txt";
        readStudentData(filename);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("----- Student Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display Students by Name (Ascending)");
            System.out.println("5. Display Students by GPA (Descending)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    removeStudent(sc);
                    break;
                case 3:
                    updateStudent(sc);
                    break;
                case 4:
                    displayStudentbyName();
                    break;
                case 5:
                    displayStudentsByGPA();
                    break;
                default:
                    System.out.println("invalid choice!, please try again!");
                    break;
            }
        }
    }

    public static void readStudentData(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double gpa = Double.parseDouble(data[2].trim());
                String city = data[3].trim();
                String university = "MIT";

                Student student = new Student(id, name, gpa, city, university);
                students.add(student);
            }

            System.out.println("Student data loaded successfully");
            br.close();
        }

        catch (IOException e)

        {
            System.out.print("an Error occured while reading data " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("number format is invalid in Student.txt" + e.getMessage());
        }

    }

    public static void addStudent(Scanner sc) {
        System.out.println("-------------Add Student------------------------");
        System.out.println("Enter Student id:");
        int id = sc.nextInt();
        System.out.println("Enter Student name:");
        String name = sc.next();
        System.out.println("Enter Student gpa:");
        double gpa = sc.nextDouble();
        System.out.println("Enter Student city:");
        String city = sc.next();

        Student student = new Student(id, name, gpa, city, "MIT");
        students.add(student);

        System.out.println("Student added successfully");

    }

    public static void removeStudent(Scanner sc) {
        System.out.println("-------------Removing student------------------");
        System.out.println("Enter the id of the student to remove!");
        int id = sc.nextInt();

        boolean found = false;
        Iterator<Student> i = students.iterator();
        while (i.hasNext()) {
            Student student = i.next();
            if (student.getId() == id) {
                i.remove();
                found = true;
                break;
            }
        }

        if (found == true) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }

    }

    public static void updateStudent(Scanner sc) {
        System.out.println("----- Update Student -----");
        System.out.print("Enter the ID of the student to update: ");
        int id = sc.nextInt();

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter Name: ");
                String name = sc.next();
                if (!name.isEmpty()) {
                    student.setName(name);
                }
                System.out.print("Enter GPA: ");
                String gpaInput = sc.next();
                if (!gpaInput.isEmpty()) {
                    double gpa = Double.parseDouble(gpaInput);
                    student.setGpa(gpa);
                }
                System.out.print("Enter City: ");
                String city = sc.next();
                if (!city.isEmpty()) {
                    student.setCity(city);
                }
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void displayStudentbyName() {
        System.out.println("----- Students by Name (Ascending) -----");
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparing(Student::getName));
        System.out.println("ID\tName\tGPA\tCity\tUniverity");
        System.out.println("******************************************");
        displayStudents(sortedStudents);
    }

    public static void displayStudentsByGPA() {
        System.out.println("----- Students by GPA (Descending) -----");
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparingDouble(Student::getGpa).reversed());
        System.out.println("ID\tName\tGPA\tCity\tUniverity");
        System.out.println("******************************************");
        displayStudents(sortedStudents);
    }

    public static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

}
