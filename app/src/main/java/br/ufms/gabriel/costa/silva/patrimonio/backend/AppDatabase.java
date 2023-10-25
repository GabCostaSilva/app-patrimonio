package br.ufms.gabriel.costa.silva.patrimonio.backend;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Objects;

import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.ObjetoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.TipoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Objeto;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Tipo;

@Database(entities = {Objeto.class, Tipo.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    private static void dropRoom(Context context) {
        context.getApplicationContext().deleteDatabase("patrimonio"); //<<<< ADDED before building Database.
//Your existing code follows
        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "patrimonio")
                // allow queries on the main thread.
                // Don't do this on a real app!
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getDatabase(Context context) {
        dropRoom(context);
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "patrimonio").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public abstract ObjetoDao objetoDao();

    public abstract TipoDao tipoDao();
}
