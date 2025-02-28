package service.BranchService;

import config.SessionFactoryInstance;
import entity.Branch;
import entity.Employee;
import repository.BranchRepostiroy.BranchRepositoryImpl;
import repository.BranchRepostiroy.BranchRepository;

import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository = new BranchRepositoryImpl();

    @Override
    public Branch save(Branch branch) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Branch saveBranch = branchRepository.save(session, branch);
                session.getTransaction().commit();
                return saveBranch;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<Branch> findById(Long id) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Branch> byId = branchRepository.findById(session, id);
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
    public List<Branch> findAll() {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                List<Branch> all = branchRepository.findAll(session);
                session.getTransaction().commit();
                return all;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public Branch update(Branch branch) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Branch> found = branchRepository.findById(session, branch.getId());
                if (found.isPresent()) {
                    found.get().setName(branch.getName());
                    found.get().setLocation(branch.getLocation());
                    branchRepository.save(session, found.get());
                } else {
                    System.out.println("Branch not found");
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
    public void delete(Branch branch) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Branch> found = branchRepository.findById(session, branch.getId());

                if (found.isPresent()) {
                    branchRepository.delete(session, branch);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Branch with id " + branch.getId() + " does not exist");
                    session.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Branch findByCode(String code) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Branch branch = branchRepository.findByCode(session, code);
                session.getTransaction().commit();
                return branch;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
