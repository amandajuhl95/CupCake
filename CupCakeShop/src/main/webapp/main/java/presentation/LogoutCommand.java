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

/**
 *
 * @author sofieamalielandt
 */
public class LogoutCommand extends Command
{

    /**
     * Removes attributes user, invoices, cart, allinvoices, bottoms and
     * toppings from session - forwards to index.jsp
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("invoices");
        session.removeAttribute("cart");
        session.removeAttribute("allinvoices");
        session.removeAttribute("bottoms");
        session.removeAttribute("toppings");

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
