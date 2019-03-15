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
public class LineItem
{

    private int invoice_id;
    private final Cupcake cupcake;
    private int quantity;
    private double price;

    /**
     * Initializes the values of a newly created LineItem, as well as calculate
     * the price of the LineItem.
     *
     * @param cupcake an object of the class Cupcake
     * @param quantity an Integer
     */
    public LineItem(Cupcake cupcake, int quantity)
    {
        this.cupcake = cupcake;
        this.quantity = quantity;
        this.price = cupcake.getPrice() * quantity;
    }

    /**
     * Initializes the values of a newly created LineItem, as well as calculate
     * the price of the LineItem.
     *
     * @param cupcake an object of the class Cupcake
     * @param quantity an Integer
     * @param invoice_id an Integer
     */
    public LineItem(Cupcake cupcake, int quantity, int invoice_id)
    {
        this.cupcake = cupcake;
        this.quantity = quantity;
        this.price = cupcake.getPrice() * quantity;
        this.invoice_id = invoice_id;
    }

    /**
     * @return the invoice_id
     */
    public int getInvoice_id()
    {
        return invoice_id;
    }

    /**
     * @return the cupcake
     */
    public Cupcake getCupcake()
    {
        return cupcake;
    }

    /**
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
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
     * Adds the value of more to the parameter quantity and recalculates the
     * price of the LineItem
     *
     * @param more an Integer to add
     */
    public void addQuantity(int more)
    {
        quantity = quantity + more;
        price = cupcake.getPrice() * quantity;
    }

}
