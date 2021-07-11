package com.gmail.vlad9880;

public final class Student {

    private final String name;
    private final int age;
    private final String group;
    private final Faculty faculty;

    public Student(String name, int age, String group, Faculty faculty) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.faculty = faculty;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getGroup(){
        return group;
    }

    public Faculty getFaculty() throws CloneNotSupportedException {
        return (Faculty) faculty.clone();
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Student) {
            Student temp = (Student)obj;
            return this.age == temp.age && group.equals(temp.group);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        int result = 31 * (group != null ? group.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }



    @Override
    public String toString(){
        return "Student [" + "name: " + name + ", age: " + age + ", group: " + group + ", faculty: " + faculty + "]";
    }

}
