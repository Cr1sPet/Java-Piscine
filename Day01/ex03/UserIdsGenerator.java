package ex03;

public class UserIdsGenerator {

    private static Integer userId;
    private static UserIdsGenerator userIdsGenerator = null;

    private UserIdsGenerator() {
        userId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null) {
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }


    public Integer generateId() {
        return ++userId;
    }

}
