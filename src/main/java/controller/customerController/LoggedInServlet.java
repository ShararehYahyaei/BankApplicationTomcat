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
        try {
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            if (userName == null || userName.trim().isEmpty() && (password == null || password.trim().isEmpty())) {
                req.setAttribute("error", "userName or password is required!");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }

            Customer byCustomerNumber = customerService.login(userName, password);
            resp.setStatus(200);
            req.setAttribute("byCustomerNumber", byCustomerNumber);
            req.getSession().setAttribute("userId", byCustomerNumber.getId());
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);

        } catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);

        }
    }
}