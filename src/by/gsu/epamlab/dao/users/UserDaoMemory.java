package by.gsu.epamlab.dao.users;

import by.gsu.epamlab.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoMemory implements UserDao {

    private final Map<String, String> users = new HashMap<String, String>() {{
        put("aaa", "aaa");
        put("bbb", "bbb");
        put("ccc", "ccc");
    }};


    @Override
    public User getUser(String login) {
        if (users.containsKey(login)) {
            return new User(login);
        } else {
            return null;
        }
    }

    @Override
    public boolean addUser(String login, String password) {
        synchronized (UserDaoMemory.class) {
            if (getUser(login) == null) {
                users.put(login, password);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return password.equals(users.get(login));
    }
}
