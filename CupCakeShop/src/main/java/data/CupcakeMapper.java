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
import logic.Bottom;
import logic.Topping;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class CupcakeMapper
{

    private final DatabaseConnector dbc = new DatabaseConnector();

    /**
     * Creates and initializes a newly created DatabaseConnector for further use
     * in methods, and sets datasource for the connector.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public CupcakeMapper() throws SQLException
    {
        DataSourceMysql dataSourceMysql = new DataSourceMysql();
        dbc.setDataSource(dataSourceMysql.getDataSource());
    }

    /**
     * Executes the query in the database to collect a list of objects from the class Bottom.
     * @return an arraylist of object from the class Bottom
     * @throws java.sql.SQLException if executing of query is not possible
     */
    public List<Bottom> getAllBottoms() throws SQLException
    {

        List<Bottom> bottoms = new ArrayList();
        dbc.open();
        String query = "SELECT * FROM cupcake_Bottoms";

        int bottom_id = 0;
        String flavour = "";
        double price = 0.0;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {

            bottom_id = rs.getInt("bottom_id");
            flavour = rs.getString("bottom_flavour");
            price = rs.getDouble("bottom_price");

            Bottom bottom = new Bottom(bottom_id, flavour, price);
            bottoms.add(bottom);

        }
        dbc.close();
        return bottoms;
    }

    /**
     * Executes the query in the database to collect a list of objects from the class Topping.
     * @return an arraylist of objects from the class Topping
     * @throws java.sql.SQLException if executing of query is not possible
     */
    public List<Topping> getAllToppings() throws SQLException
    {

        List<Topping> toppings = new ArrayList();
        dbc.open();
        String query = "SELECT * FROM cupcake_Toppings";

        int topping_id = 0;
        String flavour = "";
        double price = 0.0;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {

            topping_id = rs.getInt("topping_id");
            flavour = rs.getString("topping_flavour");
            price = rs.getDouble("topping_price");

            Topping topping = new Topping(topping_id, flavour, price);
            toppings.add(topping);

        }
        dbc.close();
        return toppings;
    }

     /**
     * Executes the query in the database to collect an object from the class Topping with a specific topping_id.
     * @param topping_id is used to detect the specific topping in the database
     * @return an object from the class Topping
     * @throws java.sql.SQLException if executing of query is not possible
     */
    public Topping getTopping(int topping_id) throws SQLException
    {

        dbc.open();
        String query = "SELECT * FROM cupcake_Toppings"
                + " WHERE topping_id = " + topping_id + ";";

        Topping topping = null;

        int id = 0;
        String flavour = "";
        double price = 0.0;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {

            id = rs.getInt("topping_id");
            flavour = rs.getString("topping_flavour");
            price = rs.getDouble("topping_price");

            topping = new Topping(id, flavour, price);

        }
        dbc.close();
        return topping;
    }

    /**
     * Executes the query in the database to collect an object from the class Bottom with a specific bottom_id.
     * @param bottom_id is used to detect the specific bottom in the database
     * @return an object from the class Bottom
     * @throws java.sql.SQLException if executing of query is not possible
     *
     */
    public Bottom getBottom(int bottom_id) throws SQLException
    {

        dbc.open();
        String query = "SELECT * FROM cupcake_Bottoms"
                + " WHERE bottom_id = " + bottom_id + ";";

        Bottom bottom = null;

        int id = 0;
        String flavour = "";
        double price = 0.0;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {

            id = rs.getInt("bottom_id");
            flavour = rs.getString("bottom_flavour");
            price = rs.getDouble("bottom_price");

            bottom = new Bottom(id, flavour, price);

        }
        dbc.close();
        return bottom;
    }
}
