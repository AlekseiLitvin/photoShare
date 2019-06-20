package by.gsu.epamlab.dao.users;


import by.gsu.epamlab.model.User;

public interface UserDao {
    User getUser(String login);

    boolean addUser(String login, String password);

    boolean checkPassword(String login, String password);
}
