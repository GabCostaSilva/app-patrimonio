package br.ufms.gabriel.costa.silva.patrimonio.backend.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Objeto;

@Dao
public interface ObjetoDao {

    @Query("SELECT * FROM Objeto WHERE numPatrimonio = :numPatrimonio LIMIT 1")
    Objeto getObjeto(int numPatrimonio);

    @Query("SELECT * FROM Objeto")
    List<Objeto> getAll();

    @Insert
    void insertAll(Objeto... objetos);

    @Update
    void update(Objeto objeto);

    @Delete
    void delete(Objeto objeto);
}
