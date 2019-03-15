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
import logic.LoginConnector;
import logic.User;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class LoginCommand extends Command
{

    /**
     * Retrieves parameters username and password, checks if combination is
     * valid to a user in the database, if true user i saved as an attribute in
     * session and forwards to CommandController with shop as path - if false
     * forwards to defaultLogin.jsp.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see logic.LoginConnector#isValid(java.lang.String, java.lang.String)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            LoginConnector lc = new LoginConnector();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            boolean valid = lc.isValid(username, password);
            if (!valid)
            {
                request.getRequestDispatcher("/defaultLogin.jsp").forward(request, response);
            }
            if (valid)
            {
                User user = lc.getUser(username);
                session.setAttribute("user", user);

                request.getRequestDispatcher("/CommandController?command=shop").forward(request, response);
            }

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
