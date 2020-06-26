package com.example.movieapp.presenter.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentLoginBinding;
import com.example.movieapp.presenter.Main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding mBinding;
    private View mView;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * <Fragment Factory Method>
     * @return
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();
        onClickListener();
        return mView;
    }

    private void onClickListener(){
        mBinding.Login.setOnClickListener(view -> {
            String userText = mBinding.userName.getEditText().getText().toString();
            String passText = mBinding.Password.getEditText().getText().toString();

            if (!userText.equals("") && !passText.equals("")){
                mBinding.userName.setErrorEnabled(false);
                mBinding.Password.setErrorEnabled(false);
                mBinding.Login.setEnabled(false);
                getActivity().startActivity(MainActivity.newIntent(getActivity()));
                getActivity().finish();
                //api request should be make;
            }
            else {
                mBinding.userName.setError(getActivity().getResources().getString(R.string.empty_field));
                mBinding.Password.setError(getActivity().getResources().getString(R.string.empty_field));
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}