package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User searchedUser = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                searchedUser = user;
                break;
            }
        }
        if (searchedUser == null) {
            throw new UserNotFoundException("Пользователь с таким именем не найден.");
        }
        return searchedUser;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("Пользователь не валидный.");
        }
        return user.isValid();
    }

    public static void main(String[] args) {
        User[] users = {new User("Petr Arsentev", true)};
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
