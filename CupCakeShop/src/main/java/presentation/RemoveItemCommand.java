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
import logic.LineItem;
import logic.ShoppingCart;

/**
 *
 * @author sofieamalielandt
 */
public class RemoveItemCommand extends Command
{

    /**
     * Retrieves parameters topping_id and bottom_id, retrieves attribute cart
     * from session, loops through the List of lineItems in cart, if a matching
     * combination of topping and bottom is found, LineItem is removed -
     * forwards to shoppingcart.jsp - if the List of LineItems is empty -
     * forwards to CommandController with shop as path.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see logic.ShoppingCart#removeCupcake(logic.LineItem)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        int topping_id = Integer.parseInt(request.getParameter("topping"));
        int bottom_id = Integer.parseInt(request.getParameter("bottom"));
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        LineItem result = null;

        for (LineItem l : cart.getLineItems())
        {
            if (l.getCupcake().getTopping().getTopping_id() == topping_id && l.getCupcake().getBottom().getBottom_id() == bottom_id)

            {
                result = l;
            }
        }
        cart.removeCupcake(result);

        if (cart.getLineItems().isEmpty())
        {
            request.getRequestDispatcher("/CommandController?command=shop").forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/shoppingcart.jsp");
    }

}
