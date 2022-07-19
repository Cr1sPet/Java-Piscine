package ex02;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface UsersList {

    void add(User user);
    User getById(Integer id) throws UserNotFoundException;
    User getByIndex(Integer index);
    Integer size();

}
