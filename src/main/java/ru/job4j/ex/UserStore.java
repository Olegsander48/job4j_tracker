package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User res = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                res = user;
                break;
            }
        }
        if (res == null) {
            throw new UserNotFoundException("Searched user not found");
        }
        return res;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (user.getUsername().length() < 3 || !user.isValid()) {
            throw new UserInvalidException("Username is not valid");
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ex) {
            ex.printStackTrace();
        } catch (UserNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
