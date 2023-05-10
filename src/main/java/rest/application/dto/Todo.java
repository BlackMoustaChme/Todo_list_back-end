package rest.application.dto;

public class Todo {
    private int id;

    private int userId;
    private String title;
    private String creationDate;
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

//    public Integer getUserId(){
//        return userId;
//    }
//
//    public void setUserId(Integer userId){
//        this.userId = userId;
//    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(String creationDate){
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
