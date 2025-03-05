package controller.customerController;

import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.accuntService.AccountServiceImpl;
import service.accuntService.AccountServiceInterface;

import java.io.IOException;

@WebServlet("/changeStatusForAccount")
public class ChangeStatusAccount extends HttpServlet {
    private final AccountServiceInterface accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/changeStatusAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accountNumber = req.getParameter("accountNumber");
            boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
            Account accountByAccountNumber = accountService.getAccountByAccountNumber(accountNumber);
            accountByAccountNumber.setActive(isActive);
            accountService.update(accountByAccountNumber);
            resp.setStatus(200);
            resp.getWriter().write("{\"message\": \"Account activated successfully\"}");
            resp.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
