/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.InvoiceConnector;
import logic.ShoppingCart;
import logic.User;

/**
 *
 * @author sofieamalielandt
 */
public class UserCommand extends Command
{

    /**
     * Removes the attribute invoice from request session, and retrives the
     * attribute User from session, a List of objects from the class
     * ShoppingCart with the user equal to the attribute user attached is also
     * retrieved, if this is not already saved in session as invoices, and
     * forwards to userpage.jsp.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see logic.InvoiceConnector#getInvoices(java.lang.String) 
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            session.removeAttribute("invoice");
            User user = (User) session.getAttribute("user");
            InvoiceConnector ic = new InvoiceConnector();

            if (session.getAttribute("invoices") == null)
            {
                List<ShoppingCart> invoices = ic.getInvoices(user.getUserName());
                session.setAttribute("invoices", invoices);
            }

            request.getRequestDispatcher("/userpage.jsp").forward(request, response);

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
