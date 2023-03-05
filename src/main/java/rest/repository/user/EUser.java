package rest.repository.user;

import java.io.Serializable;

//JPA (Begin)
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//JPA (End)

@Entity
@Table(name = "\"user\"")
public class EUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"login\"")
    private String login;

    @Column(name = "\"password\"")
    private String password;

    @Column(name = "\"email\"")
    private String email;


    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public String getUserEmail() {

        return email;
    }

    public void setUserEmail(String email) {

        this.email = email;
    }


    public String getUserPassword() {

        return password;
    }

    public void setUserPassword(String password) {

        this.password = password;
    }

    public String getUserLogin() {

        return login;
    }

    public void setUserLogin(String login) {

        this.login = login;
    }

}
