/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Bottom;
import logic.CupcakeConnector;
import logic.LineItem;
import logic.ShoppingCart;
import logic.Topping;
import logic.User;

/**
 *
 * @author sofieamalielandt
 */
public class ShopCommand extends Command
{

    /**
     * An empty List of LineItems is created, user is retrieved from session,
     * and an object from the class ShoppingCart is created - if the attributes
     * cart, bottoms and toppings is not already saved in session, this is done
     * - forwards to shop.jsp.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            CupcakeConnector cc = new CupcakeConnector();
            List<LineItem> lineitems = new ArrayList<>();
            User user = (User) session.getAttribute("user");
            ShoppingCart shoppingCart = new ShoppingCart(lineitems, user);
            if (session.getAttribute("cart") == null)
            {
                session.setAttribute("cart", shoppingCart);
            }

            if (session.getAttribute("bottoms") == null || session.getAttribute("toppings") == null)
            {
                List<Bottom> bottoms = cc.getCupcakeBottoms();
                session.setAttribute("bottoms", bottoms);
                List<Topping> toppings = cc.getCupcakeToppings();
                session.setAttribute("toppings", toppings);
            }

            request.getRequestDispatcher("/shop.jsp").forward(request, response);

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
