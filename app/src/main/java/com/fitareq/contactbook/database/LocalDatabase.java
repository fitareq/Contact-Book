package com.fitareq.contactbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.model.DataDao;
import com.fitareq.contactbook.model.ResponseObj;
import com.fitareq.contactbook.model.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ResponseObj.class, ContactData.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract DataDao dataDao();

    private static volatile LocalDatabase mINSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static LocalDatabase getInstance(Context context)
    {
        if (mINSTANCE == null)
        {
            synchronized (LocalDatabase.class)
            {
                if (mINSTANCE == null)
                {
                    mINSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "localdata").fallbackToDestructiveMigration().build();
                }
            }
        }
        return mINSTANCE;
    }
}
