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
import java.time.format.DateTimeParseException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    private final AccountServiceInterface accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/transfer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardNumberSource = req.getParameter("cardNumberSource");
        String cardNumberDestination = req.getParameter("cardNumberDestination");
        String balanceStr = req.getParameter("amount");
        Long amount = null;
        if (balanceStr != null && !balanceStr.trim().isEmpty()) {
            try {
                amount = Long.parseLong(balanceStr.trim());
            } catch (NumberFormatException e) {

                System.out.println("Invalid balance format: " + balanceStr);
                resp.setStatus(400);
                resp.getWriter().write("{\"error\": \"Invalid balance format provided\"}");
                return;
            }
        }
        String cvv2 = req.getParameter("cvv2");
        String expiryDate = req.getParameter("expiryDate");
        LocalDate expiryDateParsed = null;
        try {
            expiryDateParsed = LocalDate.parse(expiryDate);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + expiryDate);
            resp.setStatus(400);
            resp.getWriter().write("{\"error\": \"Invalid date format provided\"}");
        }
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


    }
}
