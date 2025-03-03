package controller.customerController;

import dto.LoggedInDto;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.customerService.CustomerServiceImpl;
import service.customerService.CustomerServiceInter;

import java.io.IOException;

@WebServlet("/login")
public class LoggedInServlet extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (userName == null || userName.trim().isEmpty() && (password == null || password.trim().isEmpty())) {
            req.setAttribute("error", "userName or password is required!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        Customer byCustomerNumber = customerService.login(userName, password);
        if (byCustomerNumber == null) {
            req.setAttribute("error", "No customer found with this username or password!");
        } else {
            req.setAttribute("byCustomerNumber", byCustomerNumber);
        }
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
