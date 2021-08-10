package Room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    public List<ExpenseEntity> getItems;
    public ExpenseRepository repository;

    public ExpenseViewModel(Application application){
        super(application);

        repository = new ExpenseRepository(application);
        getItems = repository.getItems;
    }

    public void insert(ExpenseEntity entity){
        repository.insert(entity);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public void update(ExpenseEntity entity){
        repository.update(entity);
    }
}

