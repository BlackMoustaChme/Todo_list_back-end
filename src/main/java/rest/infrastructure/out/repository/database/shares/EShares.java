package rest.infrastructure.out.repository.database.shares;

import jakarta.persistence.*;
import rest.infrastructure.out.repository.database.todo.ETodo;
import rest.infrastructure.out.repository.database.user.EUser;

import java.io.Serializable;

@Entity
@Table(name = "\"shares\"")
public class EShares implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"")
    private int id;
    @ManyToOne
    @JoinColumn(name = "\"user_id\"")
    private EUser user;

    @ManyToOne
    @JoinColumn(name = "\"todo_id\"")
    private ETodo todo;

    @Column(name = "\"role\"")
    private String role;

    public int getShareId() {

        return id;
    }

    public void setShareId(int id){

        this.id = id;
    }

    public EUser getUserId() {

        return user;
    }

    public void setUserId(EUser user){

        this.user = user;
    }

    public ETodo getTodoId() {

        return todo;
    }

    public void setTodoId(ETodo todo){

        this.todo = todo;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }
}
