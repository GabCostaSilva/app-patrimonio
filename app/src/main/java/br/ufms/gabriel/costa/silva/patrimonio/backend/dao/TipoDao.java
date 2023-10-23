package br.ufms.gabriel.costa.silva.patrimonio.backend.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Tipo;

@Dao
public interface TipoDao {

    @Query("SELECT * FROM Tipo WHERE id = :id LIMIT 1")
    Tipo getUser(int id);

    @Query("SELECT * FROM Tipo")
    List<Tipo> getAll();

    @Insert
    void insertAll(Tipo... tipos);

    @Update
    void update(Tipo tipo);

    @Delete
    void delete(Tipo tipo);
}
