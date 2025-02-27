package repository.BranchRepostiroy;

import entity.Branch;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class BranchRepositoryImpl implements BranchRepository {


    @Override
    public Branch save(Session session, Branch branch) {
        session.persist(branch);
        return branch;
    }

    @Override
    public Optional<Branch> findById(Session session, Long id) {
        Optional<Branch> branch = session.byId(Branch.class).loadOptional(id);
        return branch;

    }

    @Override
    public List<Branch> findAll(Session session) {

        return session.createQuery("from Branch").list();
    }

    @Override
    public void delete(Session session, Branch branch) {
        session.delete(branch);
    }

    @Override
    public Branch findByCode(Session session, String code) {
        Query<Branch> query = session.createQuery
                ("from Branch where code = :code", Branch.class);
        query.setParameter("code", code);
        return query.uniqueResult();


    }
}
