package rest.infrastructure.out.repository.shares;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import rest.application.dto.Share;
import rest.application.dto.Todo;
import rest.application.todo.api.out.IShareRepository;
import rest.infrastructure.out.repository.todo.ETodo;
import rest.infrastructure.out.repository.todo.TodoRepository;
import rest.infrastructure.out.repository.user.EUser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShareRepository implements IShareRepository {

    @PersistenceUnit(unitName = "local_pg_webapp_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;
    @Override
    public ArrayList<Todo> getSharedTodos(Integer id) throws Exception {
        ArrayList<Todo> todos = new ArrayList<>();
        try {
            String query = "SELECT s FROM EShares s WHERE s.user =:user";
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> eUserList = entityManager.createQuery("SELECT u FROM EUser u WHERE u.id = :id", EUser.class).setParameter("id", id).getResultList();
            List<EShares> eShares = entityManager.createQuery(query,EShares.class).setParameter("user", eUserList.get(0)).getResultList();
            for (EShares eShare: eShares) {
                Todo todo = new Todo();
                todo.setId(eShare.getTodoId().getTodoId());
                todos.add(todo);
            }
        } catch (Exception e) {
            Logger.getLogger(ShareRepository.class.getName()).log(Level.INFO, null, e);
            return todos;
        }
        return todos;
    }

    @Override
    public void shareTodo(Share share) throws Exception {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> eUserList = entityManager.createQuery("SELECT u FROM EUser u WHERE u.login LIKE :login", EUser.class)
                    .setParameter("login", share.getLogin()).getResultList();
            List<ETodo> eTodoList = entityManager.createQuery("SELECT t FROM ETodo t WHERE t.id = :id", ETodo.class)
                    .setParameter("id", share.getTodoId()).getResultList();
            EShares eShares = new EShares();
            eShares.setUserId(eUserList.get(0));
//            etodo.settodoOwnerName(tod.getOwnerName());
            eShares.setTodoId(eTodoList.get(0));
            eShares.setRole(share.getRole());
            entityManager.persist(eShares);
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(ShareRepository.class.getName()).log(Level.INFO, null, e);
        }
    }


    @Override
    public void deleteUser(Integer id) throws Exception {
        String query = "delete from EShares s where s.id = :id";
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.createQuery(query).setParameter("id", id).executeUpdate();//UPDATE
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(ShareRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void updateShareInfo(Share share) throws Exception {
        String query = "update EShares s set s.role = :role where s.id = :id";
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            EShares eShares = new EShares();
            eShares.setShareId(share.getId());
            eShares.setRole(share.getRole());
            entityManager.createQuery(query).setParameter("role", eShares.getShareId()).
                    setParameter("id", eShares.getRole()).
                    executeUpdate();
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }


}
