/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.User;

/**
 *
 * @author sofieamalielandt
 */
public class UserMapper
{

    private final DatabaseConnector dbc = new DatabaseConnector();

    /**
     * Creates and initializes a newly created DatabaseConnector for further use
     * in methods, and sets datasource for the connector.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public UserMapper() throws SQLException
    {
        DataSourceMysql dataSourceMysql = new DataSourceMysql();
        dbc.setDataSource(dataSourceMysql.getDataSource());

    }

    /**
     * Executes the query in the database to collect an object from the class
     * User with a specific username.
     *
     * @param username is used to detect the specific User in the database
     * @return an object from the class User
     * @throws java.sql.SQLException if executing of query is not possible
     */
    public User getUser(String username) throws SQLException
    {
        dbc.open();
        String query
                = "SELECT * "
                + "FROM Cupcakes.user "
                + "WHERE username = '" + username + "';";

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.executeQuery();

        User user = null;
        int id = 0;
        String name = "";
        String password = "";
        String email = "";
        double balance = 0;
        String type = "";

        while (rs.next())
        {
            id = rs.getInt("user_id");
            name = rs.getString("username");
            password = rs.getString("password");
            email = rs.getString("email");
            balance = rs.getDouble("balance");
            type = rs.getString("type");

            if (rs.getString("username") == null)
            {
                return null;
            }

            user = new User(id, name, password, email, balance, type);
        }
        dbc.close();
        return user;
    }

    /**
     * Executes the query in the database to collect an object from the class
     * User with a specific user_id.
     *
     * @param user_id is used to detect the specific User in the database
     * @return an object from the class User
     * @throws java.sql.SQLException if executing of query is not possible
     */
    public User getUser(int user_id) throws SQLException
    {
        dbc.open();
        String query
                = "SELECT * "
                + "FROM Cupcakes.user "
                + "WHERE user_id = '" + user_id + "';";

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.executeQuery();

        User user = null;
        int id = 0;
        String name = "";
        String password = "";
        String email = "";
        double balance = 0;
        String type = "";

        while (rs.next())
        {
            id = rs.getInt("user_id");
            name = rs.getString("username");
            password = rs.getString("password");
            email = rs.getString("email");
            balance = rs.getDouble("balance");
            type = rs.getString("type");

            if (rs.getString("username") == null)
            {
                return null;
            }

            user = new User(id, name, password, email, balance, type);
        }
        dbc.close();
        return user;
    }

    /**
     * Executes the query in the database to update the attribute balance on a
     * User with a specific user_id.
     *
     * @param user_id is used to detect the specific User in the database
     * @param balance the new value to insert on balance
     * @throws java.sql.SQLException if executing update is not possible
     */
    public void updateBalance(int user_id, double balance) throws SQLException
    {
        dbc.open();

        String query = "UPDATE `Cupcakes`.`user`"
                + "SET `balance` = ? WHERE (`user_id` = '" + user_id + "');";

        double newBalance = balance;

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setDouble(1, newBalance);
        statement.executeUpdate();

        dbc.close();
    }
    
    /**
     * Executes the query in the database to update the attributes username, password and email on a specific User.
     * @param user is used to detect the specific User in the database
     * @throws java.sql.SQLException if executing update is not possible
     */
    public void updateUser(User user) throws SQLException
    {
        dbc.open();

        String query = "UPDATE `Cupcakes`.`user`"
                + "SET `username` = ?,`password` = ?,`email` = ? WHERE (`user_id` = '" + user.getId() + "');";

        String username = user.getUserName();
        String password = user.getPassword();
        String email = user.getEmail();

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.executeUpdate();

        dbc.close();
    }

    /**
     * Executes the query in the database to insert an object from the class
     * User.
     *
     * @param newUser the User to insert in database
     * @throws java.sql.SQLException if executing update is not possible
     */
    public void newUser(User newUser) throws SQLException
    {
        dbc.open();
        String query = "INSERT INTO Cupcakes.user"
                + "(`username`, `password`, `email`,`type`)"
                + "VALUES (?,?,?,?);";

        String username = newUser.getUserName();
        String password = newUser.getPassword();
        String email = newUser.getEmail();
        String type = newUser.getType();

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setString(4, type);
        statement.executeUpdate();
        dbc.close();
    }

    /**
     * Executes the query in the database to collect a list of objects from the
     * class User.
     *
     * @return an arraylist of object from the class User
     * @throws java.sql.SQLException if executing of query is not possible
     */
    public List<User> getAllUsers() throws SQLException
    {
        dbc.open();
        List<User> users = new ArrayList();

        String query = "SELECT * FROM user";

        int id = 0;
        String userName = "";
        String password = "";
        String email = "";
        double balance = 0.0;
        String type = "";

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.executeQuery();

        while (rs.next())
        {

            id = rs.getInt("user_id");
            userName = rs.getString("username");
            password = rs.getString("password");
            email = rs.getString("email");
            balance = rs.getDouble("balance");
            type = rs.getString("type");

            User user = new User(id, userName, password, email, balance, type);
            users.add(user);

        }
        dbc.close();
        return users;
    }

}
