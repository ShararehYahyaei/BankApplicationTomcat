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
import java.util.Set;
import java.util.stream.Collectors;


@WebServlet("/createCustomer")
public class CreateCustomer extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fullName = request.getParameter("fullName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String accountTypeStr = request.getParameter("accountType");
            AccountType accountType = AccountType.valueOf(accountTypeStr.toUpperCase());
            String balanceStr = request.getParameter("balance");
            long balance = Long.parseLong(balanceStr.trim());
            String accountNumber = request.getParameter("accountNumber");
            String code = request.getParameter("code");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String customerNumber = request.getParameter("customerNumber");
            CustomerDto customerDto = CustomerDto.builder().fullName(fullName).lastName(lastName).email(email).phone(phone).accountType(accountType).balance(balance).accountNumber(accountNumber).code(code).userName(userName).password(password).customerNumber(customerNumber).build();
            Set<String> validate = customerDto.validate();
            if (!validate.isEmpty()) {
                throw new RuntimeException(String.join("\n", validate));
            }
            customerService.save(customerDto);
            response.setStatus(200);
            response.getWriter().write("{\"message\": \"Customer created successfully\"}");
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.setStatus(400);
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}