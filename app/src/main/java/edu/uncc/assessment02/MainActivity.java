package edu.uncc.assessment02;
//Aniket Sunil Shendre
//801365034
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.assessment02.models.State;
import edu.uncc.assessment02.models.User;

public class MainActivity extends AppCompatActivity implements UsersFragment.UsersFragmentListener, AddUserFragment.AddUserInterface, StatesFragment.StateListner{

    ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: For testing created some bootstrap data
        mUsers.add(new User(new State("New York", "NY"), "Bob Smith", 25, 695));
        mUsers.add(new User(new State("Nevada", "NV"), "Alice Green", 45, 370));
        mUsers.add(new User(new State("North Carolina", "NC"), "Tom Brown", 29, 760));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, UsersFragment.newInstance(mUsers), "users-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void submit(User user) {
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment!=null){
            mUsers.add(user);
            fragment.setList(mUsers);
        }

        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToState() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new StatesFragment(), "states-frag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoaddUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddUserFragment(), "add-user-frag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void deleteUser(User user) {
        mUsers.remove(user);
    }

    @Override
    public void sortByNameAsc() {
        ArrayList<User> list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list.add(mUsers.get(i));
        }
        Collections.sort(mUsers, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getName().compareTo(t1.getName());
            }
        });

        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment!=null){
            fragment.setList(list);
        }

        //getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sortByNameDsc() {
        ArrayList<User> list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list.add(mUsers.get(i));
        }
        Collections.sort(mUsers, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return t1.getName().compareTo(user.getName());
            }
        });

        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment!=null){
            fragment.setList(list);
        }

        //getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sortScoreA() {
        ArrayList<User> list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list.add(mUsers.get(i));
        }
        Collections.sort(mUsers, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getCreditScore()-t1.getCreditScore();
            }
        });

        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment!=null){
            fragment.setList(list);
        }
    }

    @Override
    public void sortScoreD() {
        ArrayList<User> list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list.add(mUsers.get(i));
        }
        Collections.sort(mUsers, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return t1.getCreditScore()-user.getCreditScore();
            }
        });

        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment!=null){
            fragment.setList(list);
        }
    }

    @Override
    public void goback() {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-frag");
        if(fragment!=null){
            fragment.setState("");
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void selectState(String state) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-frag");
        if(fragment!=null){
            fragment.setState(state);
        }
        getSupportFragmentManager().popBackStack();
    }
}