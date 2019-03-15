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
import logic.InvoiceConnector;
import logic.ShoppingCart;

/**
 *
 * @author sofieamalielandt
 */
public class CheckoutCommand extends Command
{

    /**
     * Retrieves attribute cart from session, and checks if the User's balance
     * is higher or equal to the total price of cart, if so the ShoppingCart is
     * inserted in the database and the total price is withdrawn from the user's
     * balance, and afterwards the balance is updated in the database as well
     * The string returned is saved in session as checkout, if checkout was
     * succesful, the cart is saved in session as invoice, and the attributes
     * cart and invoices are removed - forwards to checkout.jsp.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see logic.InvoiceConnector#checkout(logic.ShoppingCart) 
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            InvoiceConnector ic = new InvoiceConnector();
            String checkout = ic.checkout(cart);
            session.setAttribute("checkout", checkout);
            ShoppingCart invoice = null;

            if (checkout.equals("Thank you for your order"))
            {
                invoice = ic.getInvoice(cart.getInvoice_id());
                session.setAttribute("invoice", invoice);
                session.removeAttribute("cart");
                session.removeAttribute("invoices");
            }

            request.getRequestDispatcher("/checkout.jsp").forward(request, response);

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
