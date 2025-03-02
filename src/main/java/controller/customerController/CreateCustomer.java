package controller.customerController;

import dto.CustomerDto;
import entity.AccountType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.customerService.CustomerServiceImpl;
import service.customerService.CustomerServiceInter;

import java.io.IOException;


@WebServlet("/createCustomer")
public class CreateCustomer extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String accountTypeStr = request.getParameter("accountType");
        AccountType accountType = null;
        try {
            accountType = AccountType.valueOf(accountTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {

            System.out.println("Invalid account type: " + accountTypeStr);
            response.setStatus(400);
            response.getWriter().write("{\"error\": \"Invalid account type provided\"}");
            return;
        }

        String balanceStr = request.getParameter("balance");
        Long balance = null;
        if (balanceStr != null && !balanceStr.trim().isEmpty()) {
            try {
                balance = Long.parseLong(balanceStr.trim());
            } catch (NumberFormatException e) {

                System.out.println("Invalid balance format: " + balanceStr);
                response.setStatus(400);
                response.getWriter().write("{\"error\": \"Invalid balance format provided\"}");
                return;
            }
        }

        String accountNumber = request.getParameter("accountNumber");
        String code = request.getParameter("code");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String customerNumber = request.getParameter("customerNumber");

        CustomerDto customerDto = CustomerDto.builder()
                .fullName(fullName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .accountType(accountType)
                .balance(balance)
                .accountNumber(accountNumber)
                .code(code)
                .userName(userName)
                .password(password)
                .customerNumber(customerNumber)
                .build();


        customerService.save(customerDto);
        request.setAttribute("message", "Customer saved successfully..");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}