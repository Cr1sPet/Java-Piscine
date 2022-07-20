public class UsersArrayLIst implements UsersList {

    private User[] users;

    private int capacity;

    private int currentSize = 0;

    {
        capacity = 10;
    }

    public UsersArrayLIst() {
        users = new User[capacity];
    }

    public UsersArrayLIst(int capacity) {
        users = new User[capacity];
    }

    @Override
    public void add(User user) {
        if (currentSize == capacity) {
            capacity += capacity / 2;
            User[]temp = new User[capacity];
            for (int i = 0; i < currentSize; i++) {
                temp[i] = users[i];
            }
            temp[currentSize] = user;
        } else {
            users[currentSize] = user;
        }
        currentSize++;
    }

    @Override
    public User getById(Integer id)  {

        for (int i = 0; i < currentSize; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User by Id {" + id + "} not found" );
    }

    @Override
    public User getByIndex(Integer index) {

        if (index > 0 && index >= currentSize) {
            return null;
        }

        return users[index];
    }

    @Override
    public Integer size() {
        return currentSize;
    }
}
