package space.lala.nyxpizzaapp.datasource.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import space.lala.nyxpizzaapp.datasource.local.database.UserDao;
import space.lala.nyxpizzaapp.datasource.local.database.UsersDatabase;
import space.lala.nyxpizzaapp.model.User;

public class UsersRepository {
    private UserDao userDao;

    public UsersRepository(Application application) {
        UsersDatabase database = UsersDatabase.getInstance(application);
        userDao = database.userDao();
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    public LiveData<User> getUser(int id) {
        return userDao.getUser(id);
    }

    private void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
