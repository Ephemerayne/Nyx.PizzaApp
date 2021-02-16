package space.lala.nyxpizzaapp.ui.activitylogin;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import space.lala.nyxpizzaapp.datasource.repository.UsersRepository;
import space.lala.nyxpizzaapp.model.User;

public class RegisterDialogViewModel extends AndroidViewModel {
    private UsersRepository repository;

    public RegisterDialogViewModel(@NonNull Application application) {
        super(application);
        repository = new UsersRepository(application);
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }
}
