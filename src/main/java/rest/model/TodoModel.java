package rest.model;

import rest.model.api.dto.Todo;
import rest.model.api.in.ITodo;
import rest.model.api.out.ITodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoModel implements ITodo {

    private ITodoRepository todoRepository;
    @Override
    public void injectRepository(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

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
}
