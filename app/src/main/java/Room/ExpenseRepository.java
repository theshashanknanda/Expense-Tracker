package Room;

import android.app.Application;

import java.util.List;

public class ExpenseRepository {
    public ExpenseDao dao;
    public List<ExpenseEntity> getItems;

    public ExpenseRepository(Application application){
        ExpenseDataBase dataBase = ExpenseDataBase.getINSTANCE(application);
        dao = dataBase.Dao();
        getItems = dao.getItems();
    }

    public void insert(ExpenseEntity expense){
        dao.insert(expense);
    }

    public void delete(int id){
        dao.delete(id);
    }

    public void update(ExpenseEntity expense){
        dao.update(expense);
    }
}


