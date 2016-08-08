package by.gsu.epamlab.controllers.authorization;

import by.gsu.epamlab.model.User;

import java.util.HashSet;
import java.util.Set;

public class LoggedUsers {
    //Set of users with active session
    public static final Set<User> usersSet = new HashSet<>();
}
