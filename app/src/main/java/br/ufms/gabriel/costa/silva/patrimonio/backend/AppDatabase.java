package br.ufms.gabriel.costa.silva.patrimonio.backend;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Objects;

import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.ObjetoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Objeto;


@Database(entities = {Objeto.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "ControleDeUsuarios").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public abstract ObjetoDao objetoDao();
}

