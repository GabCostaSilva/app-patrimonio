package br.ufms.gabriel.costa.silva.patrimonio.backend.entidades;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(foreignKeys = @ForeignKey(entity = Tipo.class,
        parentColumns = "id", childColumns = "tipoId",
        onDelete = ForeignKey.CASCADE))
public class Objeto {

    @PrimaryKey(autoGenerate = true)
    private int numPatrimonio;

    private int tipoId;

    private String dataDeRegistro;

    private String nomeFuncionario;

    public Objeto(int tipoId, String nomeFuncionario) {
        this.tipoId = tipoId;
        this.dataDeRegistro = LocalDate.now().toString();
        this.nomeFuncionario = nomeFuncionario;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(String dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public int getNumPatrimonio() {
        return numPatrimonio;
    }

    public void setNumPatrimonio(int numPatrimonio) {
        this.numPatrimonio = numPatrimonio;
    }

    @Override
    public String toString() {
        return
                "Número de patrimônio " + numPatrimonio + "\n" +
                        "Tipo " + tipoId + "\n" +
                        "Data De Registro " + dataDeRegistro + "\n" +
                        "Nome do Funcionario " + nomeFuncionario;
    }
}
