package entity;

public class Student extends User {

    private String major;
    private int teacher_id;
private int grade;
    public Student() {
    }

    public Student(int id, String name, String soname, int age,int grade, String major, int teacher_id) {
        super(id, name, soname, age);
        this.grade=grade;
        this.major = major;
        this.teacher_id = teacher_id;
    }



    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "{" +
                "major='" + major + '\'' +
                ", teacher_id=" + teacher_id +
                "} " + super.toString()+"\n";
    }

}
