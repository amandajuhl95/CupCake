/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.CupcakeMapper;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aamandajuhl
 */
public class CupcakeConnector
{

    CupcakeMapper cm = null;

    /**
     * Creates and initializes a newly created CupcakeMapper for further use in
     * methods.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public CupcakeConnector() throws SQLException
    {
        cm = new CupcakeMapper();
    }

    /**
     * @return a list of object from the class Bottom
     * @throws java.sql.SQLException if retrieval not possible
     * @see data.CupcakeMapper#getAllBottoms() 
     */
    public List<Bottom> getCupcakeBottoms() throws SQLException
    {
        return cm.getAllBottoms();
    }

    /**
     * @return a list of object from the class Topping
     * @throws java.sql.SQLException if retrival not possible
     * @see data.CupcakeMapper#getAllToppings() 
     */
    public List<Topping> getCupcakeToppings() throws SQLException
    {
        return cm.getAllToppings();
    }

    /**
     * Splits the strings bottom and topping to isolate the parameter flavour
     * for both, and uses these to retrieve a specific Bottom and Topping, to
     * create a new object from the class Cupcake.
     *
     * @param bottom a String containing the price and flavour of a specific
     * Bottom
     * @param topping a String containing the price and flavour of a specific
     * Topping
     * @return an object from the class Cupcake, or null if the strings bottom
     * and topping are null or no matching Bottom and Topping is found
     * @throws java.sql.SQLException if retrival not possible
     */
    public Cupcake getCupCake(String bottom, String topping) throws SQLException
    {
        if (bottom == null || topping == null || bottom.equals("Choose bottom") || topping.equals("Choose topping"))
        {
            return null;
        }

        String[] bottomsplit = bottom.split(" ");
        bottom = bottomsplit[0];

        String[] toppingsplit = topping.split(" ");
        if (toppingsplit.length != 4)
        {
            topping = toppingsplit[0];

        } else
        {
            topping = toppingsplit[0] + " " + toppingsplit[1];
        }

        Bottom bResult = null;
        Topping tResult = null;

        List<Bottom> bottoms = getCupcakeBottoms();
        List<Topping> toppings = getCupcakeToppings();

        for (Bottom b : bottoms)
        {
            if (b.getFlavour().equals(bottom))
            {
                bResult = b;
            }
        }

        for (Topping t : toppings)
        {
            if (t.getFlavour().equals(topping))
            {
                tResult = t;
            }
        }

        Cupcake cupcake = new Cupcake(bResult, tResult);

        return cupcake;

    }

}
