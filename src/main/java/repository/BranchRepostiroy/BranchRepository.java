package repository.BranchRepostiroy;

import entity.Account;
import entity.Branch;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    Branch save(Session session, Branch branch);

    Optional<Branch> findById(Session session, Long id);

    List<Branch> findAll(Session session);

    void delete(Session session, Branch branch);

    Branch findByCode(Session session, String code);
}
