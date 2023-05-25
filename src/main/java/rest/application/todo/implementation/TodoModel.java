package rest.application.todo.implementation;

import rest.application.dto.Share;
import rest.application.dto.Todo;
import rest.application.todo.api.in.ITodo;
import rest.application.todo.api.out.Executable;
import rest.application.todo.api.out.ITimer;
import rest.application.user.api.out.Interconnectable;
import rest.application.todo.api.out.Notifiable;
import rest.application.todo.api.out.ITodoRepository;
import rest.application.todo.api.out.IShareRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoModel implements ITodo {

    private ITodoRepository todoRepository;

    private IShareRepository shareRepository;

    private Notifiable notifier;

    private ITimer timer;

    private Executable executor;

    @Override
    public void injectRepository(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void injectShareRepository(IShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public void injectNotifier(Notifiable notifier) { this.notifier = notifier; }

    @Override
    public void injectTimer(ITimer timer) { this.timer = timer; }

    @Override
    public void injectExecutor(Executable executor) { this.executor = executor; }


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
        executor.execute(()-> {
            try {
                todoRepository.updateTodo(todo, id);
            } catch (Exception e) {
                Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            }
        });
    }

    @Override
    public void shareTodo(Share share) {
        try {
            shareRepository.shareTodo(share);
        } catch (Exception e) {
            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            shareRepository.deleteUser(id);
        } catch (Exception e) {
            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void updateShareInfo(Share share) {
        executor.execute(()-> {
            try {
                shareRepository.updateShareInfo(share);
            } catch (Exception e) {
                Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public ArrayList<Todo> getSharedTodos(Integer id) {
        ArrayList<Todo> sharedTodos = new ArrayList<>();
        try {
            ArrayList<Todo> todos = shareRepository.getSharedTodos(id);
            for (Todo todo: todos) {
                try {
                    sharedTodos.add(todoRepository.getTodo(todo.getId()));
                } catch (Exception e) {
                    Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
                    return null;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            return null;
        }
        return sharedTodos;
    }

    @Override
    public void getNumberOfCheckedTodos(Integer id, String clientId) {
        executor.execute(()-> {
            try {
                long number = todoRepository.getNumberOfCheckedTodos(id);
                timer.setInfo(clientId, "You have completed " + number + " task/s. Don't forget to delete them");
            } catch (Exception e) {
                Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            }
        });
    }

    @Override
    public void notifyUser(String clientId, String message) {
        executor.execute(()-> {
            try {
                notifier.notifyUser(clientId, message);
            } catch (Exception e) {
                Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            }
        });
    }

    @Override
    public void notifyAllUsers(String message) {
        executor.execute(()-> {
            try {
                notifier.notifyAllUsers(message);
            } catch (Exception e) {
                Logger.getLogger(TodoModel.class.getName()).log(Level.INFO, null, e);
            }
        });
    }


}
