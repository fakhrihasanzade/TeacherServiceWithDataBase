package service;

import java.util.List;

public interface CommonService<T> {

    List<T> getAll();
    T getById(int id);
    void create(T user);
    void update(T user);
    void delete(int id);

}
