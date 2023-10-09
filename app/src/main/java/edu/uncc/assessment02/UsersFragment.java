package edu.uncc.assessment02;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.uncc.assessment02.databinding.FragmentUsersBinding;
import edu.uncc.assessment02.databinding.UserListItemBinding;
import edu.uncc.assessment02.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    ArrayList<User> users;

    public static final String param1 = "list";

    public UsersFragment() {
        // Required empty public constructor
    }

    public void setList(ArrayList<User> users){
        this.users = users;
    }


    public static UsersFragment newInstance(ArrayList<User> users) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(param1, users);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            users = (ArrayList<User>) getArguments().getSerializable(param1);
        }
        //Added to setup the menu
        setHasOptionsMenu(true);
    }

    FragmentUsersBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Users");
        Adapter adapter = new Adapter(getContext(), users);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.imageViewSortNameAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.sortByNameAsc();
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortNameDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.sortByNameDsc();
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortCreditScoreDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.sortScoreD();
                adapter.notifyDataSetChanged();

            }
        });

        binding.imageViewSortCreditScoreAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.sortScoreA();
                adapter.notifyDataSetChanged();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_new_user_action) {
            Log.d("demo", "onOptionsItemSelected: menu item clicked");
            listener.gotoaddUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu); //Inflate the menu

    }

    UsersFragmentListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (UsersFragmentListener) context;
    }

    interface UsersFragmentListener{
        void gotoaddUser();

        void deleteUser(User user);

        void sortByNameAsc();

        void sortByNameDsc();

        void sortScoreA();

        void sortScoreD();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

        Context context;

        ArrayList<User> list;

        public Adapter(Context context, ArrayList<User> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            UserListItemBinding binding1 = UserListItemBinding.inflate(getLayoutInflater(), parent, false);
            return new Holder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
            User user = list.get(position);
            holder.setUI(user);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            UserListItemBinding userListItemBinding;
            public Holder(@NonNull UserListItemBinding userListItemBinding) {
                super(userListItemBinding.getRoot());
                this.userListItemBinding = userListItemBinding;
            }

            void setUI(User user){
                userListItemBinding.textViewUserName.setText(user.getName());
                userListItemBinding.textViewUserAge.setText(Integer.toString(user.getAge()) + " years old");
                userListItemBinding.textViewUserState.setText(user.getState().getName()+  " " +user.getState().getAbbreviation());
                userListItemBinding.textViewCreditScore.setText(Integer.toString(user.getCreditScore()));
                int score = user.getCreditScore();
                if(score>300 && score<=579){
                    userListItemBinding.imageViewCreditScore.setImageResource(R.drawable.poor);
                } else if(score>580 && score<=669){
                    userListItemBinding.imageViewCreditScore.setImageResource(R.drawable.fair);
                } else if(score>670 && score<=739){
                    userListItemBinding.imageViewCreditScore.setImageResource(R.drawable.good);
                } else if(score>740 && score<=799){
                    userListItemBinding.imageViewCreditScore.setImageResource(R.drawable.very_good);
                }else if(score>800 && score<=850){
                    userListItemBinding.imageViewCreditScore.setImageResource(R.drawable.excellent);
                }

                userListItemBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        users.remove(user);
                        notifyDataSetChanged();
                        listener.deleteUser(user);
                    }
                });
            }
        }
    }

}