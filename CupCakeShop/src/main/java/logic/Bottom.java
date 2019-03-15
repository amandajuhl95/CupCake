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
public class Bottom
{

    private final int bottom_id;
    private final String flavour;
    private final double price;

     /**
     * Initializes the value of a newly created Bottom.
     *
     * @param bottom_id an Integer
     * @param flavour a String
     * @param price a Double
     */
    public Bottom(int bottom_id, String flavour, double price)
    {
        this.bottom_id = bottom_id;
        this.flavour = flavour;
        this.price = price;
    }

     /**
      * 
     * @return bottom_id
     */
    public int getBottom_id()
    {
        return bottom_id;
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
