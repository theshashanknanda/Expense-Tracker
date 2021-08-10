package Room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface ExpenseDao {

    @Query("SELECT * FROM expense_database")
    public List<ExpenseEntity> getItems();

    @Update
    public void update(ExpenseEntity entity);

    @Insert
    public void insert(ExpenseEntity... entities);

    @Query("DELETE FROM expense_database WHERE id=:id")
    public void delete(int id);
}
