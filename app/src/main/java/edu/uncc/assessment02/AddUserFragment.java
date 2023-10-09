package edu.uncc.assessment02;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uncc.assessment02.databinding.FragmentAddUserBinding;
import edu.uncc.assessment02.models.State;
import edu.uncc.assessment02.models.User;

public class AddUserFragment extends Fragment {

    String state = "";
    public AddUserFragment() {
        // Required empty public constructor
    }

    void setState(String state){
        this.state = state;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add User");

        String name = "";
        int age = 0;
        int creditScore = 0;

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editTextName.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "add name", Toast.LENGTH_LONG ).show();
                } else if(binding.editTextAge.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "add age", Toast.LENGTH_LONG ).show();
                }  else if(binding.editTextCreditScore.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "add credit score", Toast.LENGTH_LONG ).show();
                } else if(Integer.parseInt(binding.editTextCreditScore.getText().toString())<350 || Integer.parseInt(binding.editTextCreditScore.getText().toString())>800){
                    Toast.makeText(getActivity(), "not appropriate credit score", Toast.LENGTH_LONG ).show();
                } else if(binding.textViewState.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "select state", Toast.LENGTH_LONG ).show();
                } else {
                    String[] statestring = binding.textViewState.getText().toString().split(" ");
                    State state1 = new State(statestring[0], statestring[1]);
                    User user = new User(state1, binding.editTextName.getText().toString(),
                            Integer.parseInt(binding.editTextAge.getText().toString()), Integer.parseInt(binding.editTextCreditScore.getText().toString()));
                    listener.submit(user);
                }
            }
        });

        binding.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToState();
            }
        });

        binding.textViewState.setText(state);

    }

    AddUserInterface listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AddUserInterface) context;
    }

    public interface AddUserInterface{
        void submit(User user);

        void goToState();

    }

}