package serviceImpl;

import database.SQLConnection;
import entity.Teacher;
import service.CommonService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements CommonService<Teacher> {
    @Override
    public List<Teacher> getAll() {

        List<Teacher> teachers = new ArrayList<>();
        String query = "select *from teacher";

        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("soname"),
                        resultSet.getInt("age"),
                        resultSet.getString("subject"),
                        resultSet.getDouble("salary")
                );
                teachers.add(teacher);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teachers;
    }

    @Override
    public Teacher getById(int id) {

        Teacher teacher = null;
        String query = "select *from teacher where id=?";

        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                teacher = new Teacher(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("soname"),
                        resultSet.getInt("age"),
                        resultSet.getString("subject"),
                        resultSet.getDouble("salary")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teacher;
    }

    @Override
    public void create(Teacher user) {
        String query = "insert into teacher(id,name,soname,age,subgect,salary) values(?,?,?,?,?,?)";
        Connection connection = SQLConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setInt(4, user.getAge());
            stmt.setString(5, user.getSubject());
            stmt.setDouble(6, user.getSalary());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close(); // Bağlantını bağla
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xətaları çap et
            }

        }}

        @Override
        public void update (Teacher user){
            String query = "updade teacher set name=?,set soname=?,set age=?,set subject=?,set salary=? where id=?";
            Connection connection = SQLConnection.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getSurname());
                stmt.setInt(3, user.getAge());
                stmt.setString(4,user.getSubject());
                stmt.setDouble(5,user.getSalary());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void delete ( int id){
            String query = "delete from teacher where id=?";

            Connection connection=SQLConnection.getConnection();
            try(PreparedStatement stmt=connection.prepareStatement(query)){
                stmt.setInt(1,id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
