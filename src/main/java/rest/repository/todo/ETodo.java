package rest.repository.todo;

import java.io.Serializable;
import java.util.Date;

//JPA (Begin)
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import rest.repository.user.EUser;
//JPA (End)

@Entity
@Table(name = "\"todos\"")
public class ETodo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"")
    private int id;

    @ManyToOne
    @JoinColumn(name = "\"user_id\"")
    private EUser user;

//    @ManyToOne
//    @JoinColumn(name = "\"owner_name\"")
//    private EUser ownerName;

    @Column(name = "\"title\"")
    private String title;

    @Column(name = "\"creation_date\"")
    private String creationDate;

    @Column(name = "\"text\"")
    private String text;

    @Column(name = "\"check\"")
    private Boolean check;

    public int getTodoId() {

        return id;
    }

    public void setTodoId(int id){

        this.id = id;
    }

    public EUser getTodoUserId() {

        return user;
    }

    public void setTodoUserId(EUser user){

        this.user = user;
    }

    //    public EUser getCarOwnerName(){
//
//        return ownerName;
//    }
//
//    public void setCarOwnerName(EUser ownerName){
//
//        this.ownerName = ownerName;
//    }
    public String getTodoTitle(){

        return title;
    }

    public void setTodoTitle(String title){

        this.title = title;
    }

    public String getTodoCreationDate(){

        return creationDate;
    }

    public void setTodoCreationDate(String creationDate){

        this.creationDate = creationDate;
    }

    public String getTodoText(){

        return text;
    }

    public void setTodoText(String text){

        this.text = text;
    }

    public Boolean getTodoCheck(){

        return check;
    }

    public void setTodoCheck(Boolean check){

        this.check = check;
    }


}
