package service.customerService;


import config.SessionFactoryInstance;
import dto.CustomerDto;
import entity.Account;
import entity.Branch;
import entity.Customer;
import entity.Employee;
import repository.BranchRepostiroy.BranchRepository;
import repository.BranchRepostiroy.BranchRepositoryImpl;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;
import repository.customerRepository.CustomerRepoImpl;
import repository.customerRepository.CustomerRepository;
import service.BranchService.BranchServiceImpl;
import service.BranchService.BranchServiceInter;


import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerServiceInter {

    private final CustomerRepository customerRepository = new CustomerRepoImpl();
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final BranchServiceInter branchService = new BranchServiceImpl();



    @Override
    public Customer save(CustomerDto customer) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Customer customerNew = new Customer();
                customerNew.setFullName(customer.getFullName());
                customerNew.setLastName(customer.getLastName());
                customerNew.setEmail(customer.getEmail());
                customerNew.setPhone(customer.getPhone());
                Branch branch = branchService.findByCode( customer.getCode());
                customerNew.setBranch(branch);

                Account account = new Account();
                account.setAccountType(customer.getAccountType());
                account.setCustomer(customerNew);
                account.setBalance(customer.getBalance());
                account.setActive(true);
                account.setAccountNumber(customer.getAccountNumber());
                account.setBranch(branch);

                customerNew.setAccount(account);
                Customer saveCustomer = customerRepository.save(session, customerNew);
                accountRepository.save(session, account);
                session.getTransaction().commit();
                return saveCustomer;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Customer> byId = customerRepository.findById(session, id);
                session.getTransaction().commit();
                return byId;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                List<Customer> customers = customerRepository.findAll(session);
                session.getTransaction().commit();
                return customers;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Customer> found = customerRepository.findById(session, customer.getId());
                if (found.isPresent()) {
                    found.get().setFullName(customer.getFullName());
                    found.get().setLastName(customer.getLastName());
                    found.get().setEmail(customer.getEmail());
                    found.get().setPhone(customer.getPhone());
                    customerRepository.save(session, found.get());
                    session.getTransaction().commit();
                    return found.get();
                } else {
                    System.out.println("Customer not found");
                    session.getTransaction().rollback();

                }
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;

    }


    @Override
    public void delete(Customer customer) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                new Employee().getId();
                Optional<Customer> found = customerRepository.findById(session, customer.getId());

                if (found.isPresent()) {
                    customerRepository.delete(session, customer);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Customer with id " + customer.getId() + " does not exist");
                    session.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
