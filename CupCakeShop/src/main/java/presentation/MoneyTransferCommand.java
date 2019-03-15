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
import logic.User;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class MoneyTransferCommand extends Command
{

    /**
     * Retrieves parameter amount and retrives attribute user from session, and
     * updates user's balance with amount - forwards to CommandController with
     * shop as path.
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
            Double amount = Double.parseDouble(request.getParameter("amount"));
            User user = (User) session.getAttribute("user");
            user.setBalance(amount);

            request.getRequestDispatcher("/CommandController?command=shop").forward(request, response);

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
