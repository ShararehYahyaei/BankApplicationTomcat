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
@WebServlet("/getCustomer")
public class GetCustomer extends HttpServlet {
    private final CustomerServiceInter customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerNumber = req.getParameter("customerNumber");
        if (customerNumber == null || customerNumber.trim().isEmpty()) {
            req.setAttribute("error", "Customer Number is required!");
            req.getRequestDispatcher("/getCustomer.jsp").forward(req, resp);
            return;
        }

        Customer byCustomerNumber = customerService.findByCustomerNumber(customerNumber);
        if (byCustomerNumber == null) {
            req.setAttribute("error", "No customer found with this number!");
        } else {
            req.setAttribute("byCustomerNumber", byCustomerNumber);
        }
        req.getRequestDispatcher("/getCustomer.jsp").forward(req, resp);
    }
}
