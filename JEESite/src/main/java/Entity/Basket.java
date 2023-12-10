package Entity;

import jakarta.persistence.*;

@Entity
public class Basket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idBasket", nullable = false)
    private int idBasket;
    @Basic
    @Column(name = "idProduct", nullable = false)
    private int idProduct;
    @Basic
    @Column(name = "idUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public int getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(int idBasket) {
        this.idBasket = idBasket;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
