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
        try {
            String cardNumber = req.getParameter("cardNumber");
            String cvv2 = req.getParameter("cvv2");
            String expiryDateStr = req.getParameter("expiryDate");
            String password = req.getParameter("password");
            String customerNumber = req.getParameter("customerNumber");
            LocalDate expiryDate = LocalDate.parse(expiryDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String accountNumber = req.getParameter("accountNumber");
            CardDto cardDto = CardDto.builder()
                    .cardNumber(cardNumber)
                    .cvv2(cvv2)
                    .expiryDate(expiryDate)
                    .password(password)
                    .customerNumber(customerNumber)
                    .accountNumber(accountNumber)
                    .build();
            cardDto.validate();
            cardService.save(cardDto);
            resp.setStatus(200);
            resp.getWriter().write("{\"message\": \"Customer created successfully\"}");
            resp.sendRedirect("/index.jsp");

        }catch (Exception e){
            resp.setStatus(400);
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);

        }
    }
}

