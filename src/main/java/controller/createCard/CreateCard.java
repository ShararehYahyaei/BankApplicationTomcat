package controller.createCard;

import dto.CardDto;
import dto.CustomerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.cardRepository.CardRepository;
import repository.cardRepository.CardRepositoryImpl;
import service.cardService.CardService;
import service.cardService.CardServiceInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/createCard")
public class CreateCard extends HttpServlet {
    private final CardServiceInterface cardService = new CardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createCard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardNumber = req.getParameter("cardNumber");
        String cvv2 = req.getParameter("cvv2");
        String expiryDateStr = req.getParameter("expiryDate");
        String password = req.getParameter("password");
        String customerNumber = req.getParameter("customerNumber");

        if (cardNumber == null || cardNumber.trim().isEmpty() ||
                cvv2 == null || cvv2.trim().isEmpty() ||
                expiryDateStr == null || expiryDateStr.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                customerNumber == null || customerNumber.trim().isEmpty()) {

            req.setAttribute("error", "تمام فیلدها الزامی هستند!");
            req.getRequestDispatcher("/createCard.jsp").forward(req, resp);
            return;
        }
        LocalDate expiryDate;
        try {
            expiryDate = LocalDate.parse(expiryDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            req.setAttribute("error", "فرمت تاریخ معتبر نیست!");
            req.getRequestDispatcher("/createCard.jsp").forward(req, resp);
            return;
        }
        CardDto cardDto = CardDto.builder()
                .cardNumber(cardNumber)
                .cvv2(cvv2)
                .expiryDate(expiryDate)
                .password(password)
                .customerNumber(customerNumber)
                .build();
        cardService.save(cardDto);
        req.setAttribute("message", "Card saved successfully..");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}

