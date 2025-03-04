package controller.transfer;

import dto.TransferDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.accuntService.AccountServiceImpl;
import service.accuntService.AccountServiceInterface;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    private final AccountServiceInterface accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/transfer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String cardNumberSource = req.getParameter("cardNumberSource");
            String cardNumberDestination = req.getParameter("cardNumberDestination");
            String balanceStr = req.getParameter("amount");
            Long amount = null;
            if (balanceStr != null && !balanceStr.trim().isEmpty()) {
                try {
                    amount = Long.parseLong(balanceStr.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount format: " + balanceStr);
                    resp.setStatus(400);
                    resp.getWriter().write("{\"error\": \"Invalid amount format provided\"}");
                    return;
                }
            }

            String cvv2 = req.getParameter("cvv2");
            String expiryDate = req.getParameter("expiryDate");
            LocalDate expiryDateParsed = LocalDate.parse(expiryDate);
            String password = req.getParameter("password");
            String customerNumber = req.getParameter("customerNumber");
            TransferDto dtoT = new TransferDto();
            dtoT.setCardNumberSource(cardNumberSource);
            dtoT.setCardNumberDestination(cardNumberDestination);
            dtoT.setAmount(amount);
            dtoT.setCvv2(cvv2);
            dtoT.setExpiryDate(expiryDateParsed);
            dtoT.setPassword(password);
            dtoT.setCustomerNumber(customerNumber);
            dtoT.validate();
            accountService.transfer(dtoT);
            resp.setStatus(200);
            resp.getWriter().write("{\"message\": \"Transfer created successfully\"}");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

    }

}
