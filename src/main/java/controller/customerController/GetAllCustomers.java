package controller.customerController;

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

@WebServlet("/getAllCustomers")
public class GetAllCustomers extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> all = customerService.findAll();
        request.setAttribute("customers", all);
        request.getRequestDispatcher("/getAllCustomers.jsp").forward(request, response);
    }

}
