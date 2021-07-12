package com.gmail.vlad9880;

public class Main {

    static boolean compare(Student student, Student student2){
        if (student.hashCode() == student2.hashCode())
            return student.equals(student2);
        return false;
    }

    public static void main(String[] args) {

        Student student = new Student("Vlad", 23, "Summer", new Faculty("Equipment", "ADD"));
        Student student2 = new Student("Alexa", 23, "Summer", new Faculty("Psychology", "FSO"));

        System.out.println(compare(student, student2));

        System.out.println(student);

        Faculty f = null;
        try {
            f = student.getFaculty();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        f.setDepartment("FSO");

        System.out.println(student);

    }
}
