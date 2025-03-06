package controller.createCard;

import entity.Card;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.cardService.CardService;
import service.cardService.CardServiceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/changePasswordForCard")
public class ChangePasswordForCard extends HttpServlet {
    private final CardServiceInterface cardService = new CardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String customerUsername = session.getAttribute("currentCustomer").toString();
        List<String> cardNumbers = cardService.fetchByUserName(customerUsername);
        if(cardNumbers.isEmpty()) {
            req.setAttribute("error", "this user doesnt have any card");
        }
        req.setAttribute("cardNumbers", cardNumbers);
        req.getRequestDispatcher("/changePasswordForCard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String cardNumber = req.getParameter("cardNumbers");
            System.out.println(cardNumber);
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