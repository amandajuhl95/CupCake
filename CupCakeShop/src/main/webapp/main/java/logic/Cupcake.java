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
public class Cupcake
{

    private final Bottom bottom;
    private final Topping topping;
    private final double price;

    /**
     * Initializes the values of a newly created Cupcake, as well as calculate
     * the price of the Cupcake.
     *
     * @param bottom an object of the class Bottom
     * @param topping an object of the class Topping
     */
    public Cupcake(Bottom bottom, Topping topping)
    {
        this.bottom = bottom;
        this.topping = topping;
        this.price = bottom.getPrice() + topping.getPrice();
    }

    /**
     * @return the bottom
     */
    public Bottom getBottom()
    {
        return bottom;
    }

    /**
     * @return the topping
     */
    public Topping getTopping()
    {
        return topping;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Compares this Cupcake to the specified Cupcake, the result is true, if
     * and only if the argument is not null and the Cupcake object represents
     * the same Bottom and Topping flavour as this object.
     *
     * @param cupcake the object to compare this Cupcake against
     * @return true if the given Cupcake represents a Cupcake equivalent to this
     * Cupcake. false otherwise
     */
    public boolean compareCupcake(Cupcake cupcake)
    {
        return this.getBottom().getFlavour().equals(cupcake.getBottom().getFlavour()) && this.getTopping().getFlavour().equals(cupcake.getTopping().getFlavour());
    }

    /**
     * @return a string with the bottom flavour and topping falvour
     */
    @Override
    public String toString()
    {
        return bottom.getFlavour() + " with " + topping.getFlavour();
    }

}
