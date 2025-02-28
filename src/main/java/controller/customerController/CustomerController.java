package controller.customerController;

import com.alibaba.fastjson2.JSON;
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


@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp
    ) throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            List<Customer> allCustomers = customerService.findAll();
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(allCustomers);
            resp.setContentType("application/json");
            resp.getWriter().write(json);

        } catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setStatus(200);
            ObjectMapper objectMapper = new ObjectMapper();
            CustomerDto customer = objectMapper.readValue(request.getReader(), CustomerDto.class);
            customerService.save(customer);
            System.out.println("Received Customer: " + customer.getFullName());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Customer received successfully\"}");

        } catch (Exception e) {
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Failed to process request: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

}
