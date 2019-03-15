/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class ShoppingCart
{

    private int invoice_id;
    private List<LineItem> lineItems;
    private double totalPrice;
    private User user;
    private String date;

    /**
     * Initializes the values of a newly created ShoppingCart.
     *
     * @param lineitems a list of objects from the class LineItem
     * @param user an object from the class User
     */
    public ShoppingCart(List<LineItem> lineitems, User user)
    {
        this.lineItems = lineitems;
        this.totalPrice = calcTotalPrice();
        this.user = user;
    }

    /**
     * Initializes the values of a newly created ShoppingCart.
     *
     * @param lineitems a list of objects from the class LineItem
     * @param user an object from the class User
     * @param invoice_id an Integer
     * @param date a string
     */
    public ShoppingCart(List<LineItem> lineitems, User user, int invoice_id, String date)
    {
        this.lineItems = lineitems;
        this.totalPrice = calcTotalPrice();
        this.user = user;
        this.invoice_id = invoice_id;
        this.date = date;
    }

    /**
     * Loops through the lineItems to calculate the total price of the
     * ShoppingCart.
     *
     * @return the price of the ShoppingCart
     *
     */
    private double calcTotalPrice()
    {
        double price = 0;

        for (LineItem l : lineItems)
        {
            price += l.getPrice();
        }

        return price;
    }

    /**
     * @return the invoice_id
     */
    public int getInvoice_id()
    {
        return invoice_id;
    }

    /**
     * Sets the invoice_id to invoice_id.
     *
     * @param invoice_id an Integer
     */
    public void setInvoice_id(int invoice_id)
    {
        this.invoice_id = invoice_id;
    }

    /**
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * @return a List of objects from the class LineItem
     */
    public List<LineItem> getLineItems()
    {
        return lineItems;
    }

    /**
     * @return a double total price
     * @see #calcTotalPrice()
     */
    public double getTotalPrice()
    {
        totalPrice = calcTotalPrice();

        return totalPrice;
    }

    /**
     * @return the date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Compares this LineItem to the lineItems in the List LineItems, the result
     * is false, if and only if the Cupcake of the LineItem represents the same
     * Cupcake as an other LineItem in the List of lineItems and adds this
     * object from the class LineItem to the List lineItems - if the result is
     * true and the Cupcake of the LineItem already is represented in a
     * LineItem, the quantity is added to the exsisting LineItem instead.
     *
     * @param i an object from the class LineItem
     */
    public void addCupcake(LineItem i)
    {
        boolean add = true;

        for (LineItem l : lineItems)
        {
            if (l.getCupcake().compareCupcake(i.getCupcake()))
            {
                add = false;
                l.addQuantity(i.getQuantity());

            }
        }

        if (add)
        {
            lineItems.add(i);
        }
    }

    /**
     * Removes the LineItem from the List of lineItems.
     * @param i an object from the class LineItem
     */
    public void removeCupcake(LineItem i)
    {
        lineItems.remove(i);
    }

    /**
     * @return a string with the invoice_id and date
     */
    @Override
    public String toString()
    {
        return "Ordernumber: " + invoice_id + " " + date;
    }

}
