package service.BranchService;

import entity.Account;
import entity.Branch;
import entity.Customer;

import java.util.List;
import java.util.Optional;

public interface BranchServiceInter {
    Branch save(Branch branch);
    Optional<Branch> findById(Long id);

    List<Branch> findAll();

    Branch update(Branch branch);

    void delete(Branch branch);
    Branch findByCode(String code);
}
