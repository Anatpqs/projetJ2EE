package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class CommandLinePK implements Serializable {
    @Column(name = "idCommand")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommand;
    @Column(name = "line_number")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lineNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandLinePK that = (CommandLinePK) o;

        if (idCommand != that.idCommand) return false;
        if (lineNumber != that.lineNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCommand;
        result = 31 * result + lineNumber;
        return result;
    }
}
