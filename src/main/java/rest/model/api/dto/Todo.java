package rest.model.api.dto;

import java.util.Date;

public class Todo {
    private int id;

    private int userId;
    private String title;
    private Date creationDate;
    private String text;
    private boolean check;


    public Todo(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Date getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public Boolean getCheck(){
        return check;
    }

    public void setCheck(Boolean check){
        this.check = check;
    }



}
