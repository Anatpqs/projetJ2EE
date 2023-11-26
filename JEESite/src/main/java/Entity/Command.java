package Entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Command {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCommand")
    private int idCommand;
    @Basic
    @Column(name = "idUser")
    private Integer idUser;
    @Basic
    @Column(name = "date")
    private Date date;

    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
