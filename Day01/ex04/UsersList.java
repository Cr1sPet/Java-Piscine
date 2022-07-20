package ex04;

public interface UsersList {

    void add(User user);
    User getById(Integer id) throws UserNotFoundException;
    User getByIndex(Integer index);
    Integer size();

}
