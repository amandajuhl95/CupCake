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

/**
 *
 * @author aamandajuhl and sofieamalielandt
 */
public abstract class Command
{

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * The path is isolated, and a suitable command is determined on behalf of
     * this
     *
     * @param request a HttpServletRequest
     * @return the suitable command
     */
    public static Command from(HttpServletRequest request)
    {
        Command c;
        String path = request.getParameter("command");

        switch (path)
        {
            case "login":
                c = new LoginCommand();
                break;

            case "newuser":
                c = new NewUserCommand();
                break;

            case "shop":
                c = new ShopCommand();
                break;

            case "shoppingcart":
                c = new ShoppingcartCommand();
                break;

            case "checkout":
                c = new CheckoutCommand();
                break;

            case "moneytransfer":
                c = new MoneyTransferCommand();
                break;

            case "removeItem":

                c = new RemoveItemCommand();
                break;

            case "user":
                c = new UserCommand();
                break;

            case "userinformation":
                c = new UserinfoCommand();
                break;

            case "update":
                c = new UserupdateCommand();
                break;

            case "admin":
                c = new AdminCommand();
                break;

            case "invoice":
                c = new InvoiceCommand();
                break;

            case "admininvoice":
                c = new AdminnvoiceCommand();
                break;

            case "logout":
                c = new LogoutCommand();
                break;

            default:
                c = new UnknownCommand();
        }

        return c;
    }

}
