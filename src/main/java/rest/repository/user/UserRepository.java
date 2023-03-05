package rest.repository.user;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import rest.model.api.dto.User;
import rest.model.api.out.IUserRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserRepository implements IUserRepository {

    @PersistenceUnit(unitName = "local_pg_webapp_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;


    @Override
    public boolean registerUser(String login, String password, String email) throws Exception {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            rest.repository.user.EUser eUser = new rest.repository.user.EUser();
            eUser.setUserLogin(login);
            eUser.setUserPassword(password);
            eUser.setUserEmail(email);
            entityManager.persist(eUser);
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean authUser(String login, String password) throws Exception {
//        "SELECT c FROM Customer c WHERE c.name LIKE :custName")
        String query = "SELECT u FROM EUser u WHERE u.login LIKE :login and u.password LIKE :password";
        Integer size = 0;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<rest.repository.user.EUser> userList = entityManager.createQuery(query, rest.repository.user.EUser.class).setParameter("login", login).setParameter("password", password).getResultList();
            size = userList.size();
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
        }
        return size == 1;
//        if (size == 1) return true;
//        else return false;
//        return false;
    }

    @Override
    public User getUser(String login) throws Exception {
        User user = new User();;
        try {
            String query = "SELECT u FROM EUser u WHERE u.login LIKE :login";
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            EUser eUser = entityManager.createQuery(query, EUser.class).setParameter("login", login).getSingleResult();
            user.setId(eUser.getUserId());
            user.setLogin(eUser.getUserLogin());
            user.setEmail(eUser.getUserEmail());
        } catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
            throw new RuntimeException(e);
        }
        return user;
    }
}
