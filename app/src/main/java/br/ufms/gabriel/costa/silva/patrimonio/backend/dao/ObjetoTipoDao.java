package br.ufms.gabriel.costa.silva.patrimonio.backend.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.ObjetoTipo;

@Dao
public interface ObjetoTipoDao {

    @Query("SELECT Objeto.numPatrimonio AS numPatrimonio, Objeto.dataDeRegistro as dataRegistro," +
            "Objeto.nomeFuncionario as nomeFuncionario, Tipo.tipo as tipo," +
            "Tipo.descricao as descricao " +
            "FROM Objeto INNER JOIN Tipo ON Objeto.tipoId = Tipo.id"
    )
    List<ObjetoTipo> getAllObjetoTipo();
}
