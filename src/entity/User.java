package entity;

public class User {

    private int id;
    private String name;
    private String soname;
    private int age;

    public User() {
    }

    public User(int id, String name, String soname, int age) {
        this.id = id;
        this.name = name;
        this.soname = soname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return soname;
    }

    public void setSurname(String soname) {
        this.soname = soname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + soname + '\'' +
                ", age=" + age +
                '}';
    }

}
