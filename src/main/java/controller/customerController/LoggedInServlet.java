package controller.customerController;

import dto.LoggedInDto;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.cardService.CardService;
import service.customerService.CustomerServiceImpl;
import service.customerService.CustomerServiceInter;

import java.io.IOException;
import java.util.List;

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
            customerService.login(userName, password);
            HttpSession session = req.getSession();
            session.setAttribute("currentCustomer", userName);

            resp.setStatus(200);
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);

        } catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);

        }
    }
}