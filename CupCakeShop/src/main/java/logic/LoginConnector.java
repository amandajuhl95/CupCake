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
 * @author aamandajuhl and sofieamalielandt
 */
public class LoginConnector
{

    UserMapper um = null;

    /**
     * Creates and initializes a newly created UserMapper for further use in
     * methods.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public LoginConnector() throws SQLException
    {
        um = new UserMapper();
    }

    /**
     * Compares the strings username and password to all the User's in the
     * database, the result is true, if and only if the argument is not null and
     * the username and password represents the same username and password as an
     * User in the database.
     *
     * @param username a string
     * @param password a string
     * @return true if the given username and password represents a User
     * equivalent to this a User in the database. false otherwise
     * @throws java.sql.SQLException if retrieval of Users's is not possible
     */
    public boolean isValid(String username, String password) throws SQLException
    {
        List<User> users = um.getAllUsers();
        User result = null;

        if (username == null || username.isEmpty())
        {
            return false;
        }
        if (password == null || password.isEmpty())
        {
            return false;
        }

        for (User u : users)
        {
            if (u.getUserName().equals(username))
            {
                result = u;
            }
        }
        if (result == null)
        {
            return false;
        } else if (result.getPassword().equals(password))
        {
            return true;
        }

        return false;
    }

    /**
     * @param username is used to detect the specific User
     * @return an object from the class User
     * @throws java.sql.SQLException if retrieval not possible
     * @see data.UserMapper#getUser(java.lang.String)
     */
    public User getUser(String username) throws SQLException
    {
        return um.getUser(username);
    }

    /**
     * Compares the string username to all the User's username in the database,
     * and compares oldpassword to all the User's password in the database - If
     * and only if the a User is not null and the username and oldpassword
     * represents the same username and password as an User in the database, is
     * the User's data updated.
     *
     * @param user an object from the class User - the User to be updated
     * @param username a string
     * @param email a string
     * @param oldpassword a string - to compare to User's current password
     * @param newpassword a string
     * @return a String telling whether or not the update was successful
     * @throws java.sql.SQLException if retrieval of Users's or update of User
     * is not possible
     * @see data.UserMapper#updateUser(logic.User) 
     */
    public String updateUser(User user, String username, String email, String newpassword, String oldpassword) throws SQLException
    {
        boolean notInUse = true;
        boolean valid = false;
        List<User> users = um.getAllUsers();

        for (User u : users)
        {
            if (u.getUserName().equals(username))
            {
                if (u.getId() != user.getId())
                {
                    notInUse = false;
                }
            }

            if (u.getId() == user.getId())
            {
                if (u.getPassword().equals(oldpassword) && newpassword.equals(u.getPassword()))
                {
                    valid = true;
                } else if (u.getPassword().equals(oldpassword))
                {
                    user.setPassword(newpassword);
                    valid = true;
                }
            }
        }

        if (notInUse && valid)
        {
            user.setUserName(username);
            user.setEmail(email);
            um.updateUser(user);
            return "Information saved";

        } else if (notInUse && !valid)
        {
            return "Wrong password";
        }

        return "Username already in use";
    }

}
