package Room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ExpenseEntity.class}, version = 1)
public abstract class ExpenseDataBase extends RoomDatabase {

    public abstract ExpenseDao Dao();
    public static ExpenseDataBase INSTANCE;

    public static ExpenseDataBase getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ExpenseDataBase.class ,
                    "expenseDatabase").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }
}
