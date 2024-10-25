package serviceImpl;

import database.SQLConnection;
import entity.Student;
import service.CommonService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements CommonService<Student> {
    @Override
    public List<Student> getAll() {

        List<Student> students = new ArrayList<>();
        String query = "select *from student";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("soname"),
                        resultSet.getInt("age"),
                        resultSet.getInt("grade"),
                        resultSet.getString("major"),
                        resultSet.getInt("teacher_id"));

                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    @Override
    public Student getById(int id) {
        Student student = null;

        String query = "select *from student where id=?";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)
        ) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("soname"),
                        resultSet.getInt("age"),
                        resultSet.getInt("grade"),
                        resultSet.getString("major"),
                        resultSet.getInt("teacher_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    @Override
    public void create(Student user) {

        String query = "insert into student(id,name,soname,age, grade, major,teacher_id) values(?,?,?,?,?,?,?)";
        Connection connection = SQLConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setInt(4, user.getAge());
            stmt.setInt(5, user.getGrade());
            stmt.setString(6, user.getMajor());
            stmt.setInt(7, user.getTeacher_id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Xətaları çap et
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close(); // Bağlantını bağla
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xətaları çap et
            }
        }

    }

    @Override
    public void update(Student user) {
        String query = "update student set name=?,soname=?,age=?,grade=?,major=?,teacher_id=? where id=?";
        Connection connection = SQLConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setInt(3, user.getAge());
            stmt.setInt(4, user.getGrade());
            stmt.setString(5, user.getMajor());
            stmt.setInt(6, user.getTeacher_id());
            stmt.setInt(7,user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from student where id=?";

        Connection connection=SQLConnection.getConnection();
        try(PreparedStatement stmt=connection.prepareStatement(query)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
