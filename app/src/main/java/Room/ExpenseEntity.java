package Room;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "expense_database")
public class ExpenseEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "itemName")
    public String itemName;

    @ColumnInfo(name = "paymentType")
    public String paymentType;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "category")
    public String category;
}
