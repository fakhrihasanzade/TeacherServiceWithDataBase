import entity.Student;
import serviceImpl.StudentServiceImpl;

public class Main {
    public static void main(String[] args) {
        StudentServiceImpl st=new StudentServiceImpl();

        st.update(new Student(1,"Fexri","Hesenzade",26,85,"Computer Science",1));

        System.out.println(st.getAll());

    }
}