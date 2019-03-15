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
public class InvoiceCommand extends Command
{

    /**
     * Retrieves parameter selected and uses this parameter as invoice_id for
     * retrieval of an invoice, this invoice is saved in session as invoice, and
     * forwards to invoice.jsp.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see logic.InvoiceConnector#getInvoice(int)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            int invoice_id = Integer.parseInt(request.getParameter("selected"));
            InvoiceConnector ic = new InvoiceConnector();
            ShoppingCart invoice = ic.getInvoice(invoice_id);
            session.setAttribute("invoice", invoice);

            request.getRequestDispatcher("/invoice.jsp").forward(request, response);

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
