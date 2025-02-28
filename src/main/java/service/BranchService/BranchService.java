package service.BranchService;

import entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    Branch save(Branch branch);
    Optional<Branch> findById(Long id);

    List<Branch> findAll();

    Branch update(Branch branch);

    void delete(Branch branch);
    Branch findByCode(String code);
}
