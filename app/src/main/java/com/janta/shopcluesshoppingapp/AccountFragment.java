package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RelativeLayout mRvHelpSupport, mRvMyOrder, mRvWishlist, logout;
    private FirebaseAuth mAuth;
    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRvHelpSupport = view.findViewById(R.id.rvHelpSupport);
        mRvMyOrder = view.findViewById(R.id.rvMyOrder);
        mRvWishlist = view.findViewById(R.id.rvWishlist);
        logout = view.findViewById(R.id.logOut);

        mRvHelpSupport.setOnClickListener(this);
        mRvMyOrder.setOnClickListener(this);
        mRvWishlist.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.rvHelpSupport) {
            startActivity(new Intent(getContext(), Contact.class));
        } else if(v.getId() == R.id.rvMyOrder) {
            startActivity(new Intent(getContext(), OrderActivity.class));
        }else if(v.getId() == R.id.rvWishlist) {

        } else if(v.getId() == R.id.logOut) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getContext(), "You have logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), LogInActivity.class));
        }
    }
}