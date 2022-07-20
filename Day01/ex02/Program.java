package ex02;

public class Program {

    public static void main(String[] args) {

        UsersList usersList = new UsersArrayLIst();

        System.out.println(usersList.size());

        for (int i = 0; i < 10; i++) {
            usersList.add(new User("user" + i, i * 10));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(usersList.getByIndex(i));
        }
        System.out.println(usersList.size());


        System.out.println(usersList.getById(100));


    }

}
