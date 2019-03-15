/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Cupcake;
import logic.CupcakeConnector;
import logic.LineItem;
import logic.ShoppingCart;

/**
 *
 * @author sofieamalielandt
 */
public class ShoppingcartCommand extends Command
{

    /**
     * Retrieves parameters bottom, topping and quantity, if bottom and topping
     * are not empty a cupcake with mathcing bottom and topping is retrived and
     * added to cart with the quantity as a LineItem - the attribute cart is
     * saved in session and forwards to shoppingcart.jsp - if one or more of the
     * parameters are null a String reply is saved in session - forwards to shop.jsp.
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
            String bottom = request.getParameter("bottom");
            String topping = request.getParameter("topping");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            if (bottom != null && topping != null)
            {
                CupcakeConnector cc = new CupcakeConnector();
                Cupcake cupcake = cc.getCupCake(bottom, topping);
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

                if (cupcake != null)
                {
                    cart.addCupcake(new LineItem(cupcake, quantity));
                    session.setAttribute("cart", cart);
                    request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
                }

            } else if (bottom == null || topping == null)
            {
                String reply = "Please choose a bottom and a topping for your cupcake";
                session.setAttribute("reply", reply);
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
            } else
            {
                request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
            }

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
