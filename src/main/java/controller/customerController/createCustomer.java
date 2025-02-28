package controller.customerController;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CustomerDto;
import entity.AccountType;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.customerService.CustomerServiceImpl;
import service.customerService.CustomerServiceInter;

import java.io.IOException;
import java.util.List;


@WebServlet("/createCustomer")
public class createCustomer extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        try {
//            response.setStatus(200);
//            ObjectMapper objectMapper = new ObjectMapper();
//            CustomerDto customer = objectMapper.readValue(request.getReader(), CustomerDto.class);
//            customerService.save(customer);
//            System.out.println("Received Customer: " + customer.getFullName());
//            response.setContentType("application/json");
//            response.getWriter().write("{\"message\": \"Customer received successfully\"}");
//
//        } catch (Exception e) {
//            response.setStatus(400);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"error\": \"Failed to process request: " + e.getMessage() + "\"}");
//            e.printStackTrace();
//        }


        String fullName = request.getParameter("fullName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String accountTypeStr = request.getParameter("accountType");

        // تبدیل accountTypeStr به AccountType enum با مدیریت خطا
        AccountType accountType = null;
        try {
            accountType = AccountType.valueOf(accountTypeStr.toUpperCase());  // تبدیل به enum
        } catch (IllegalArgumentException e) {
            // مدیریت خطا در صورت اشتباه بودن مقدار accountType
            System.out.println("Invalid account type: " + accountTypeStr);
            response.setStatus(400);
            response.getWriter().write("{\"error\": \"Invalid account type provided\"}");
            return;
        }

        String balanceStr = request.getParameter("balance");
        Long balance = null;
        if (balanceStr != null && !balanceStr.trim().isEmpty()) {
            try {
                balance = Long.parseLong(balanceStr.trim());  // تبدیل مقدار به Long
            } catch (NumberFormatException e) {
                // مدیریت خطا در صورت اشتباه بودن فرمت balance
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

        // ساخت CustomerDto و ذخیره آن
        CustomerDto customerDto = CustomerDto.builder()
                .fullName(fullName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .accountType(accountType)  // استفاده از accountType به عنوان enum
                .balance(balance)
                .accountNumber(accountNumber)
                .code(code)
                .userName(userName)
                .password(password)
                .customerNumber(customerNumber)
                .build();

        // ذخیره مشتری در سرویس
        customerService.save(customerDto);

        // ارسال پیغام موفقیت به JSP
        request.setAttribute("message", "Customer saved successfully..");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}