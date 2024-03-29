package rest.infrastructure.out.repository.database.todo;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import rest.application.dto.Todo;
import rest.application.api.out.todo.ITodoRepository;
import rest.infrastructure.out.repository.database.user.EUser;

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
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
            return todos;
        }
        return todos;
    }

    @Override
    public Todo getTodo(Integer id) throws Exception {
        Todo todo = new Todo();
        try {
            String query = "SELECT t FROM ETodo t WHERE t.id =:id";
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            ETodo eTodos = entityManager.createQuery(query,ETodo.class).setParameter("id", id).getSingleResult();
            todo.setId(eTodos.getTodoId());
//                tod.setOwnerName(etodo.gettodoOwnerName());
            todo.setTitle(eTodos.getTodoTitle());
            todo.setCreationDate(eTodos.getTodoCreationDate());
            todo.setText(eTodos.getTodoText());
            todo.setCheck(eTodos.getTodoCheck());
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
            return todo;
        }
        return todo;
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
            entityManager.createQuery(query).setParameter("id", id).executeUpdate();//UPDATE
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void updateTodo(Todo todo, Integer id) throws Exception {
        String query = "update ETodo t set t.title = :title, t.creationDate = :creationDate, t.text = :text, " +
                "t.check = :check where t.id = :id";
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            ETodo eTodo = new ETodo();
            eTodo.setTodoId(id);
            eTodo.setTodoTitle(todo.getTitle());
            eTodo.setTodoCreationDate(todo.getCreationDate());
            eTodo.setTodoText(todo.getText());
            eTodo.setTodoCheck(todo.getCheck());
            entityManager.createQuery(query).setParameter("title", eTodo.getTodoTitle()).
                    setParameter("creationDate", eTodo.getTodoCreationDate()).
                    setParameter("text", eTodo.getTodoText()).
                    setParameter("check", eTodo.getTodoCheck()).
                    setParameter("id", eTodo.getTodoId()).
                    executeUpdate();
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public long getNumberOfCheckedTodos(Integer id) throws Exception{
        String query = "select count(t) from ETodo t where t.user = :user_id and t.check = true ";
        long number = 20;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> eUserList = entityManager.createQuery("SELECT u FROM EUser u WHERE u.id = :id", EUser.class).setParameter("id", id).getResultList();
            ETodo eTodo = new ETodo();
            eTodo.setTodoUserId(eUserList.get(0));
            number = (Long) entityManager.createQuery(query).setParameter("user_id", eTodo.getTodoUserId()).getSingleResult();
            userTransaction.commit();
            entityManager.close();
        } catch (Exception e) {
            userTransaction.rollback();
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
            return number;
        }
        return number;
    }
}
