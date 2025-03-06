package controller.customerController;

import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.customerService.CustomerServiceImpl;
import service.customerService.CustomerServiceInter;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/changePasswordForUser")
public class ChangePasswordForUser extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/changePasswordForUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            String userName =  session.getAttribute("currentCustomer").toString();
            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");

            Optional<Customer> optionalCustomer = customerService.findByPassword(oldPassword,userName);

            if (optionalCustomer.isPresent()) {
                Customer customerN = optionalCustomer.get();
                customerN.setPassword(newPassword);
                customerService.update(customerN);
                req.setAttribute("message", "change password successful");
            } else {
                req.setAttribute("error", "current  password is incorrect");
            }

            req.getRequestDispatcher("/changePasswordForUser.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "please try again");
            req.getRequestDispatcher("/changePasswordForUser.jsp").forward(req, resp);
        }
    }
}
