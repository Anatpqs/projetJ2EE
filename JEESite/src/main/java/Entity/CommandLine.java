package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "command_line", schema = "mydb", catalog = "")
@IdClass(CommandLinePK.class)
public class CommandLine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCommand")
    private int idCommand;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "line_number")
    private int lineNumber;
    @Basic
    @Column(name = "idProduct")
    private int idProduct;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "line_price")
    private Integer linePrice;

    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(Integer linePrice) {
        this.linePrice = linePrice;
    }
}
