/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.InvoiceMapper;
import data.UserMapper;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class InvoiceConnector
{

    InvoiceMapper im = null;
    UserMapper um = null;

    /**
     * Creates and initializes a newly created UserMapper and InvoiceMapper for
     * further use in methods.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public InvoiceConnector() throws SQLException
    {
        im = new InvoiceMapper();
        um = new UserMapper();
    }

    /**
     * Checks if the User's balance is higher or equal to the total price of
     * cart, if so the ShoppingCart is inserted in the database and the total
     * price is withdrawn from the user's balance, and afterwards the balance is
     * updated in the database as well. 
     * @param cart the ShoppingCart to be inserted in database 
     * @return a String telling whether the witdraw was possible or if balance to low
     * @throws java.sql.SQLException if insert or update is not possible
     * @see data.InvoiceMapper#newInvoice(logic.ShoppingCart) 
     * @see User#pay(double) 
     * @see data.UserMapper#updateBalance(int, double) 

     */
    public String checkout(ShoppingCart cart) throws SQLException
    {
        if (cart.getTotalPrice() <= cart.getUser().getBalance())
        {
            im.newInvoice(cart);
            cart.getUser().pay(cart.getTotalPrice());
            um.updateBalance(cart.getUser().getId(), cart.getUser().getBalance());
            return "Thank you for your order";
        }

        return "Your balance is to low, to place the order";
    }

    /**
     * @param invoice_id is used to detect the specific invoice
     * @return an object from the class ShoppingCart
     * @throws java.sql.SQLException if retrieval not possible
     * @see data.InvoiceMapper#getInvoice(int) 
     */
    public ShoppingCart getInvoice(int invoice_id) throws SQLException
    {
        return im.getInvoice(invoice_id);
    }

    /**
     * @param username is used to detect objects from the class ShoppingCart, with the specific user attached
     * @return a list of object from the class ShoppingCart
     * @throws java.sql.SQLException if retrieval not possible
     * @see data.InvoiceMapper#getInvoices(java.lang.String) 
     */
    public List<ShoppingCart> getInvoices(String username) throws SQLException
    {
        return im.getInvoices(username);
    }

    /**
     * @return a list of object from the class ShoppingCart
     * @throws java.sql.SQLException if retrieval not possible
     * @see data.InvoiceMapper#getAllInvoices() 
     */
    public List<ShoppingCart> getAllInvoices() throws SQLException
    {
        return im.getAllInvoices();
    }

}
