package Entity;

import jakarta.persistence.*;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "admin")
    private Byte admin;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "loyalty_point")
    private Integer loyaltyPoint;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "is_validate")
    private Byte isValidate;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getAdmin() {
        return admin;
    }

    public void setAdmin(Byte admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(Integer loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(Byte isValidate) {
        this.isValidate = isValidate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
