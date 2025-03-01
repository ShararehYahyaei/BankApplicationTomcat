package controller.customerController.accountServlet;

import entity.Account;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.accuntService.AccountServiceImpl;
import service.accuntService.AccountServiceInterface;
import service.customerService.CustomerServiceImpl;
import service.customerService.CustomerServiceInter;

import java.io.IOException;

@WebServlet("/getAccount")
public class GetAccount extends HttpServlet {
    private final AccountServiceInterface accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerNumber = req.getParameter("customerNumber");
        if (customerNumber == null || customerNumber.trim().isEmpty()) {
            req.setAttribute("error", "Customer Number is required!");
            req.getRequestDispatcher("/getAccount.jsp").forward(req, resp);
            return;
        }


        Account accountByCustomerNumber = accountService.getAccountByCustomerNumber(customerNumber);
        if (accountByCustomerNumber == null) {
            req.setAttribute("error", "No Account found with this number!");
        } else {
            req.setAttribute("accountByCustomerNumber", accountByCustomerNumber);
        }
        req.getRequestDispatcher("/getAccount.jsp").forward(req, resp);
    }
}
