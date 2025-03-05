package controller.accountServlet;

import dto.AccountDto;
import dto.CustomerDto;
import entity.AccountType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String customerNumber = req.getParameter("customerNumber");
            String accountTypeStr = req.getParameter("accountType");
            AccountType accountType = AccountType.valueOf(accountTypeStr.toUpperCase());
            String balanceStr = req.getParameter("balance");
            long balance = Long.parseLong(balanceStr.trim());
            String accountNumber = req.getParameter("accountNumber");

            AccountDto accountDto = AccountDto.builder()
                    .customerNumber(customerNumber)
                    .accountType(accountType)
                    .balance(balance)
                    .accountNumber(accountNumber)
                    .build();
            Set<String> validate = accountDto.validate();
            if (!validate.isEmpty()) {
                throw new RuntimeException(String.join("\n", validate));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
