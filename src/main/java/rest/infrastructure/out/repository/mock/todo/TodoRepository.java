package rest.infrastructure.out.repository.mock.todo;

import rest.application.api.out.todo.ITodoRepository;
import rest.application.dto.Todo;
import rest.infrastructure.builder.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mock
public class TodoRepository implements ITodoRepository {

    private final static HashMap<Integer, Todo> mapIdTodo = new HashMap<>();
    @Override
    public ArrayList<Todo> getUserTodos(Integer id) throws Exception {
        ArrayList<Todo> todos = new ArrayList<>();
        Todo todo;
        try {
//            for (Integer i = 0; i <= mapIdTodo.size(); i++) {
//                tod/o = mapIdTodo.get(i);
//                if (to/do.getUserId() == id) {
//                    todos.add(to/do);
//                }
//            }
            for(Map.Entry<Integer, Todo> entry : mapIdTodo.entrySet()) {
                if (entry.getValue().getUserId() == id) {
                    todos.add(entry.getValue());
                }
            }
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
            return todos;
        }
        return todos;
    }

    @Override
    public Todo getTodo(Integer id) throws Exception {
        Todo todo = new Todo();
        try {
            todo = mapIdTodo.get(id);
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
            return todo;
        }
        return todo;
    }

    @Override
    public void addTodo(Todo todo, Integer userId) throws Exception {
        int iterator = 0;
        for (Map.Entry<Integer, Todo> entry : mapIdTodo.entrySet()) {
            iterator = entry.getKey();
        }
        todo.setUserId(userId);
        todo.setId(iterator + 1);
        try {
            mapIdTodo.put(iterator + 1, todo);
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void deleteTodo(Integer id) throws Exception {
        try {
            mapIdTodo.remove(id);
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }

    }

    @Override
    public void updateTodo(Todo todo, Integer id) throws Exception {
        todo.setId(id);
        try {
            for (Map.Entry<Integer, Todo> entry : mapIdTodo.entrySet()) {
                if (entry.getValue().getId() == todo.getId()) {
                    entry.getValue().setTitle(todo.getTitle());
                    entry.getValue().setText(todo.getText());
                    entry.getValue().setCheck(todo.getCheck());
                }
            }
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public long getNumberOfCheckedTodos(Integer id) throws Exception {
        long number = 0;
        Todo todo;
        try {
//            for (Integer i = 0; i <= mapIdTodo.size(); i++) {
//                tod/o = mapIdTodo.get(i);
//                if (tod/o.getUserId() == id && tod/o.getCheck()) {
//                    number = number + 1;
//                }
//            }
            for (Map.Entry<Integer, Todo> entry : mapIdTodo.entrySet()) {
                if (entry.getValue().getUserId() == id && entry.getValue().getCheck()) {
                    number = number + 1;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(TodoRepository.class.getName()).log(Level.INFO, null, e);
        }
        return number;
    }
}
