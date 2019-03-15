/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class Topping
{

    private int topping_id;
    private String flavour;
    private double price;

    /**
     * Initializes the value of a newly created Topping.
     *
     * @param topping_id an Integer
     * @param flavour a String
     * @param price a Double
     */
    public Topping(int topping_id, String flavour, double price)
    {
        this.topping_id = topping_id;
        this.flavour = flavour;
        this.price = price;
    }

    /**
     *
     * @return topping_id
     */
    public int getTopping_id()
    {
        return topping_id;
    }

    /**
     * @return the flavour
     */
    public String getFlavour()
    {
        return flavour;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @return a string with the values of falvour and price
     */
    @Override
    public String toString()
    {
        return flavour + " " + price + " kr";
    }
}
