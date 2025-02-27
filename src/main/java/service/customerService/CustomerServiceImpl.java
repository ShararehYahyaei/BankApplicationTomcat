package service.customerService;


import config.SessionFactoryInstance;
import dto.CustomerDto;
import entity.*;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;
import repository.customerRepository.CustomerRepoImpl;
import repository.customerRepository.CustomerRepository;
import repository.transactionRepository.TransactionRepository;
import repository.transactionRepository.TransactionRepositoryImpl;
import service.BranchService.BranchServiceImpl;
import service.BranchService.BranchService;
import service.transactionService.TransactionServiceImpl;
import service.transactionService.TransactionService;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerServiceInter {

    private final CustomerRepository customerRepository = new CustomerRepoImpl();
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final BranchService branchService = new BranchServiceImpl();
    private final TransactionRepository transactionService = new TransactionRepositoryImpl();


    @Override
    public Customer save(CustomerDto customerDto) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Branch branch = branchService.findByCode(customerDto.getCode());
                Customer customer = createCustomer(customerDto, branch);
                Account account = createAccount(customerDto, customer, branch);
                Customer saveCustomer = customerRepository.save(session, customer);
                accountRepository.save(session, account);
                Transaction transaction=Transaction.builder()
                        .transactionDate(LocalDate.now())
                        .type(TransactionType.Deposit)
                        .amount(account.getBalance())
                        .source(null)
                        .destination(account)
                        .build();
                transactionService.save(session, transaction);
                session.getTransaction().commit();
                return saveCustomer;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    private static Account createAccount(CustomerDto customerDto, Customer customer, Branch branch) {
        Account account = new Account();
        account.setAccountType(customerDto.getAccountType());
        account.setCustomer(customer);
        account.setBalance(customerDto.getBalance());
        account.setActive(true);
        account.setAccountNumber(customerDto.getAccountNumber());
        account.setBranch(branch);
        customer.setAccount(account);
        return account;
    }

    private static Customer createCustomer(CustomerDto customerDto, Branch branch) {
        Customer customer = new Customer();
        customer.setFullName(customerDto.getFullName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setBranch(branch);
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(customerDto.getPassword());
        customer.setCustomerNumber(customerDto.getCustomerNumber());
        return customer;
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
