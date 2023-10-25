package br.ufms.gabriel.costa.silva.patrimonio.backend.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tipo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String  tipo;

    private String descricao;

    public Tipo(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Tipo ").append(tipo).append("\n")
                .append("Descrição ").append(descricao)
                .toString();
    }
}
