package rest.application.dto;

public class Share {

    private int id;

    private int todoId;

    private int userId;

    private String login;

    private String role;

    public int getId() {

        return id;
    }

    public void setId(int id){

        this.id = id;
    }

    public int getTodoId() {

        return todoId;
    }

    public void setTodoId(int todoId){

        this.todoId = todoId;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId){

        this.userId = userId;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }
}
