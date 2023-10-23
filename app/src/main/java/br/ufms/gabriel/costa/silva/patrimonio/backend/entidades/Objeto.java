package br.ufms.gabriel.costa.silva.patrimonio.backend.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Objeto {

    @PrimaryKey(autoGenerate = true)
    private int numPatrimonio;

    private int tipoId;

    private String dataDeRegistro;

    private String nomeFuncionario;

    public Objeto(int tipoId, String dataDeRegistro, String nomeFuncionario) {
        this.tipoId = tipoId;
        this.dataDeRegistro = dataDeRegistro;
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
}
