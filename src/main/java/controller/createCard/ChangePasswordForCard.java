package controller.createCard;

import entity.Card;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.cardService.CardService;
import service.cardService.CardServiceInterface;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/changePasswordForCard")
public class ChangePasswordForCard extends HttpServlet {
    private final CardServiceInterface cardService = new CardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/changePasswordForCard.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cardNumber = req.getParameter("cardNumber");
            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");

            Card byCardNumber = cardService.findByCardNumber(cardNumber);

            if (byCardNumber != null) {
                if (byCardNumber.getPassword().equals(oldPassword)) {

                    byCardNumber.setPassword(newPassword);
                    cardService.UpdateCard(byCardNumber);

                    req.setAttribute("message", "change password successful");
                } else {
                    req.setAttribute("message", "old password does not match");
                }
            } else {
                req.setAttribute("message", "card not found");
            }

            req.getRequestDispatcher("/changePasswordForCard.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "please try again");
            req.getRequestDispatcher("/changePasswordForCard.jsp").forward(req, resp);
        }
    }
}
