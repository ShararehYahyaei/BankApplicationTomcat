package controller.customerController;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CustomerDto;
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
//
//    @Override
//    protected void doGet(HttpServletRequest req,
//                         HttpServletResponse resp
//    ) throws ServletException, IOException {
//        resp.setContentType("application/json");
//
//        try {
//            List<Customer> allCustomers = customerService.findAll();
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(allCustomers);
//            resp.setContentType("application/json");
//            resp.getWriter().write(json);
//
//        } catch (Exception e) {
//            resp.setStatus(400);
//            e.printStackTrace();
//        }
//    }

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
        String accountType = request.getParameter("accountType");
        String balance = request.getParameter("balance");
        String accountNumber = request.getParameter("accountNumber");
        String code = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String customerNumber = request.getParameter("customerNumber");

        CustomerDto customerDto = new CustomerDto();
        customerService.save(customerDto);
        request.setAttribute("message", "Customer saved successfully..");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
