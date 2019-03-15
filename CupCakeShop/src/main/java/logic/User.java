/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.UserMapper;
import java.sql.SQLException;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class User
{

    private int id;
    private String userName;
    private String password;
    private String email;
    private double balance;
    private String type;

     /**
     * Initializes the values of a newly created User.
     *
     * @param id an Integer
     * @param userName a String
     * @param password a String
     * @param email a String
     * @param balance a Double
     * @param type a String
     */
    public User(int id, String userName, String password, String email, double balance, String type)
    {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.type = type;
    }

     /**
     * Initializes the values of a newly created User, initializes type as customer and balance as 0.
     *
     * @param userName a String
     * @param password a String
     * @param email a String
     */
    public User(String userName, String password, String email)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.balance = 0.0;
        this.type = "customer";
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * Sets the userName to userName.
     *
     * @param userName a String
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the password to password.
     *
     * @param password a String
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the Email to email.
     *
     * @param email a String
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * Adds money to the balance and updates the balance to the database.
     *
     * @param money a Double
     * @throws java.sql.SQLException if update not possible
     * @see data.UserMapper#updateBalance(int, double) 
     */
    public void setBalance(double money) throws SQLException
    {
        UserMapper um = new UserMapper();
        this.balance = balance + money;
        um.updateBalance(id, balance);
    }

     /**
     * Subtracts price from the balance.
     *
     * @param price a Double
     */
    public void pay(double price)
    {
        this.balance = balance - price;
    }

    /**
     * @return a string with the values of id, userName, email and balance
     */
    @Override
    public String toString()
    {
        return "User: " + id + ", " + userName + " email: " + email + " balance: " + balance;
    }

}
