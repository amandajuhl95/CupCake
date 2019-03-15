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
import logic.NewUserConnector;
import logic.User;

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public class NewUserCommand extends Command
{

    /**
     * Retrieves parameters username, password and email, creates object User
     * and adds object to the database, a string is returned as repsonse -
     * forwards to newuser.jsp.
     *
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see logic.NewUserConnector#addUser(logic.User)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        try
        {
            HttpSession session = request.getSession();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            User u = new User(username, password, email);
            NewUserConnector c = new NewUserConnector();
            String res = c.addUser(u);
            session.setAttribute("res", res);

            request.getRequestDispatcher("/newuser.jsp").forward(request, response);

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
