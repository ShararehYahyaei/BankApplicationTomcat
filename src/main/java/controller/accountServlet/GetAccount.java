package controller.accountServlet;

import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.accuntService.AccountServiceImpl;
import service.accuntService.AccountServiceInterface;

import java.io.IOException;
import java.util.List;

@WebServlet("/getAccount")
public class GetAccount extends HttpServlet {
    private final AccountServiceInterface accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String customerNumber = req.getParameter("customerNumber");
            if (customerNumber == null || customerNumber.trim().isEmpty()) {
                req.setAttribute("error", "Customer Number is required!");
                req.getRequestDispatcher("/getAccount.jsp").forward(req, resp);
                return;
            }
            List<Account> accounts = accountService.getAccountsByCustomerNumber(customerNumber);
                req.setAttribute("accountByCustomerNumber", accounts);
            req.getRequestDispatcher("/getAccount.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/getAccount.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
