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
import logic.Cupcake;
import logic.LineItem;
import logic.ShoppingCart;
import logic.Topping;
import logic.User;

/**
 *
 * @author sofieamalielandt
 */
public class InvoiceMapper
{

    private final DatabaseConnector dbc = new DatabaseConnector();
    private UserMapper um = null;
    private CupcakeMapper cm = null;

    /**
     * Creates and initializes a newly created DatabaseConnector, UserMapper and
     * CupcakeMapper for further use in methods, and sets datasource for the
     * connector.
     *
     * @throws java.sql.SQLException if initializing not possible
     */
    public InvoiceMapper() throws SQLException
    {
        DataSourceMysql dataSourceMysql = new DataSourceMysql();
        dbc.setDataSource(dataSourceMysql.getDataSource());

        um = new UserMapper();
        cm = new CupcakeMapper();
    }

    /**
     * Executes the query in the database to collect an object from the class
     * ShoppingCart with a specific invoice_id.
     *
     * @param invoice_id is used to detect the specific invoice in the database
     * @return an object from the class ShoppingCart
     * @throws java.sql.SQLException if executing of query is not possible
     * @see #getLineItems(int) 
     */
    public ShoppingCart getInvoice(int invoice_id) throws SQLException
    {
        ShoppingCart invoice = null;

        dbc.open();
        String query = "SELECT * FROM invoice"
                + " WHERE invoice_id = " + invoice_id + ";";

        int id = 0;
        int user_id = 0;
        List<LineItem> lineItems;
        User user = null;
        String date;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            id = rs.getInt("invoice_id");
            user_id = rs.getInt("user_id");
            user = um.getUser(user_id);
            date = rs.getString("order_date");
            lineItems = getLineItems(id);

            invoice = new ShoppingCart(lineItems, user, id, date);
        }

        dbc.close();
        return invoice;
    }

    /**
     * Executes the query in the database to collect a list of objects from the
     * class ShoppingCart with a specific user attached.
     *
     * @param username used to detect tne user
     * @return an arraylist of objects from the class ShoppingCart
     * @throws java.sql.SQLException if executing of query is not possible
     * @see #getLineItems(int) 
     */
    public List<ShoppingCart> getInvoices(String username) throws SQLException
    {
        List<ShoppingCart> invoices = new ArrayList();

        dbc.open();
        String query = "SELECT invoice_id, total_price, order_date FROM Cupcakes.user join invoice on (user.user_id = invoice.user_id)"
                + " WHERE username = '" + username + "';";

        int invoice_id = 0;
        List<LineItem> lineItems;
        User user = um.getUser(username);
        String date;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            invoice_id = rs.getInt("invoice_id");
            date = rs.getString("order_date");
            lineItems = getLineItems(invoice_id);

            ShoppingCart cart = new ShoppingCart(lineItems, user, invoice_id, date);
            invoices.add(cart);
        }

        dbc.close();
        return invoices;
    }

    /**
     * Executes the query in the database to collect a list of objects from the
     * class ShoppingCart.
     *
     * @return an arraylist of object from the class ShoppingCart
     * @throws java.sql.SQLException if executing of query is not possible
     * @see #getLineItems(int) 
     */
    public List<ShoppingCart> getAllInvoices() throws SQLException
    {
        List<ShoppingCart> invoices = new ArrayList();

        dbc.open();
        String query = "SELECT * "
                + " FROM Cupcakes.invoice;";

        int invoice_id = 0;
        int user_id = 0;
        List<LineItem> lineItems;
        String date;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            invoice_id = rs.getInt("invoice_id");
            lineItems = getLineItems(invoice_id);
            user_id = rs.getInt("user_id");
            User user = um.getUser(user_id);
            date = rs.getString("order_date");

            ShoppingCart cart = new ShoppingCart(lineItems, user, invoice_id, date);
            invoices.add(cart);
        }

        dbc.close();
        return invoices;
    }

    /**
     * Executes the query in the database to collect List of obejcts from the
     * class LineItem with a specific invoice_id.
     *
     * @param invoice_id is used to detect the specific lineitems
     * @return an arraylist of object from the class LineItem
     * @throws java.sql.SQLException if executing of query is not possible
     */
    private List<LineItem> getLineItems(int invoice_id) throws SQLException
    {
        List<LineItem> lineitems = new ArrayList();

        dbc.open();
        String query = "SELECT * FROM invoice_has_items"
                + " WHERE invoice_id = " + invoice_id + ";";

        int id = 0;
        int topping_id = 0;
        int bottom_id = 0;
        int quantity = 0;

        PreparedStatement stmt = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            id = rs.getInt("invoice_id");
            topping_id = rs.getInt("topping_id");
            bottom_id = rs.getInt("bottom_id");
            quantity = rs.getInt("quantity");

            Topping topping = cm.getTopping(topping_id);
            Bottom bottom = cm.getBottom(bottom_id);
            Cupcake cupcake = new Cupcake(bottom, topping);

            LineItem i = new LineItem(cupcake, quantity, invoice_id);
            lineitems.add(i);

        }
        //dbc.close();
        return lineitems;
    }

    /**
     * Executes the query in the database to insert an object from the class
     * ShoppingCart as well as the lineitems in the ShoppingCart into the
     * database, with the newly retrieved invoice_id.
     *
     * @param cart the ShoppingCart to insert in database
     * @throws java.sql.SQLException if executing update is not possible
     */
    public void newInvoice(ShoppingCart cart) throws SQLException
    {
        dbc.open();

        String query = "INSERT INTO Cupcakes.invoice"
                + "( `user_id`, `total_price`)"
                + "VALUES (?,?);";

        int user_id = cart.getUser().getId();
        double totalprice = cart.getTotalPrice();
        int invoice_id = 0;

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, user_id);
        statement.setDouble(2, totalprice);
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next())
        {
            invoice_id = rs.getInt(1);
            cart.setInvoice_id(invoice_id);
        }

        for (LineItem i : cart.getLineItems())
        {
            i.setInvoice_id(invoice_id);
            addItem(i);
        }

        dbc.close();

    }

    /**
     * Executes the query in the database to insert an object from the class
     * LineItem into the database.
     * @param i the LineItem to insert in database
     * @throws java.sql.SQLException if executing update is not possible
     */
    private void addItem(LineItem i) throws SQLException
    {
        String query = "INSERT INTO Cupcakes.invoice_has_items"
                + "(`invoice_id`, `topping_id`, `bottom_id`, `quantity`, `price`)"
                + "VALUES (?,?,?,?,?);";

        PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

        int invoice = i.getInvoice_id();
        System.out.println(invoice);
        int topping_id = i.getCupcake().getTopping().getTopping_id();
        int bottom_id = i.getCupcake().getBottom().getBottom_id();
        int quantity = i.getQuantity();
        double price = i.getPrice();

        statement.setInt(1, invoice);
        statement.setInt(2, topping_id);
        statement.setInt(3, bottom_id);
        statement.setInt(4, quantity);
        statement.setDouble(5, price);
        statement.executeUpdate();
    }
}
