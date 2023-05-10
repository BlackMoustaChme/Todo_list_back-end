package rest.application.todo.implementation;

import rest.application.dto.Todo;
import rest.application.todo.api.in.ITodo;
import rest.application.todo.api.out.Notifiable;
import rest.application.todo.api.out.ITodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoModel implements ITodo {

    private ITodoRepository todoRepository;

    private Notifiable notifier;
    @Override
    public void injectRepository(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void injectAsync(Notifiable notifier) { this.notifier = notifier; }

    @Override
    public ArrayList<Todo> getUserTodos(Integer id) {
        try {
            return todoRepository.getUserTodos(id);
        } catch (Exception e) {
            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTodo(Todo todo, Integer id) {
        try {
            todoRepository.addTodo(todo, id);
        } catch (Exception e)
        {
            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void deleteTodo(List<Todo> todosList) {
        for (Todo todo : todosList) {
            try {
                todoRepository.deleteTodo(todo.getId());
            } catch (Exception e) {
                Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            }
        }
    }

    @Override
    public void updateTodo(Todo todo, Integer id) {
        try {
            todoRepository.updateTodo(todo, id);
        } catch (Exception e) {
            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
        }
    }

//    @Override
//    public void getNumberOfCheckedTodos(Integer id, String clientId) {
//        try {
//            int number = todoRepository.getNumberOfCheckedTodos(id);
//            notifier.notify(clientId, "You have completed" + number + "task/s. Don't forget to delete them");
//        } catch (Exception e) {
//            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
//        }
//    }
}
