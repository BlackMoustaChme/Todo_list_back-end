package rest.repository.todo;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import rest.model.api.dto.Todo;
import rest.model.api.out.ITodoRepository;
import rest.repository.user.EUser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoRepository implements ITodoRepository {

    @PersistenceUnit(unitName = "local_pg_webapp_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;
    @Override
    public ArrayList<Todo> getUserTodos(Integer id) throws Exception {
        ArrayList<Todo> todos = new ArrayList<>();
        try {
            String query = "SELECT t FROM ETodo t WHERE t.user =:user";
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> eUserList = entityManager.createQuery("SELECT u FROM EUser u WHERE u.id = :id", EUser.class).setParameter("id", id).getResultList();
            List<ETodo> eTodos = entityManager.createQuery(query,ETodo.class).setParameter("user", eUserList.get(0)).getResultList();
            for (ETodo eTodo: eTodos) {
                Todo todo = new Todo();
                todo.setId(eTodo.getTodoId());
//                tod.setOwnerName(etodo.gettodoOwnerName());
                todo.setTitle(eTodo.getTodoTitle());
                todo.setCreationDate(eTodo.getTodoCreationDate());
                todo.setText(eTodo.getTodoText());
                todo.setCheck(eTodo.getTodoCheck());
                todos.add(todo);
            }
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
            return todos;
        }
        return todos;
    }

    @Override
    public void addTodo(Todo todo, Integer userId) throws Exception {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> eUserList = entityManager.createQuery("SELECT u FROM EUser u WHERE u.id = :id", EUser.class).setParameter("id", userId).getResultList();
            ETodo eTodo = new ETodo();
            eTodo.setTodoUserId(eUserList.get(0));
//            etodo.settodoOwnerName(tod.getOwnerName());
            eTodo.setTodoTitle(todo.getTitle());
            eTodo.setTodoCreationDate(todo.getCreationDate());
            eTodo.setTodoText(todo.getText());
            eTodo.setTodoCheck(todo.getCheck());
            entityManager.persist(eTodo);
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void deleteTodo(Integer id) throws Exception {
        String query = "delete from ETodo t where t.id = :id";
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.createQuery(query).setParameter("id", id).executeUpdate();
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }
}
