/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.UserMapper;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aamandajuhl
 */
public class NewUserConnector
{

    private UserMapper um = null;

    /**
     * Creates and initializes a newly created UserMapper for further use in
     * methods.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public NewUserConnector() throws SQLException
    {
        um = new UserMapper();
    }

    /**
     * Compares this User to the User's in the database, the result is true, if
     * and only if the User is not null and the User object represents the same
     * username as an User from the database.
     *
     * @param user the object to compare User in the database against
     * @return true if the given User represents a username equivalent to an
     * User's username in the database. false otherwise
     * @throws java.sql.SQLException if retrieval is not possible
     */
    public boolean userExists(User user) throws SQLException
    {
        List<User> users = um.getAllUsers();

        for (User u : users)
        {
            if (u.getUserName().equals(user.getUserName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * The User is inserted into the database if and only if the User is not
     * null and the User object does not represents the same username as an User from the
     * database.
     *
     * @param user the User to insert in database
     * @return a string telling whether the insert is succesful or not
     * @throws java.sql.SQLException if insert is not possible
     * @see #userExists(logic.User) 
     * @see data.UserMapper#newUser(logic.User) 
     */
    public String addUser(User user) throws SQLException
    {
        if (user.getUserName().isEmpty() || user.getUserName() == null)
        {
            return "Please enter valid username";
        }
        if (user.getPassword().isEmpty() || user.getPassword() == null)
        {
            return "Please enter valid password";
        }
        if (user.getEmail().isEmpty() || user.getEmail() == null)
        {
            return "Please enter valid e-mail";
        } else if (userExists(user) == true)
        {
            return "User already exists!";
        }

        um.newUser(user);
        return "User is now created";

    }

}
