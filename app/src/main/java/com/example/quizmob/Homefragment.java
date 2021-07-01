package com.example.quizmob;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizmob.databinding.ActivityMainBinding;
import com.example.quizmob.databinding.FragmentHomefragmentBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Homefragment extends Fragment {

    FirebaseFirestore database;



    public Homefragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentHomefragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomefragmentBinding.inflate(inflater,container,false);
        database= FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        ArrayList<Categorymodel> categories =new ArrayList<>();

        categoryadpter adpter=new categoryadpter(getContext(),categories);

        database.collection("categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for(DocumentSnapshot snapshot:value.getDocuments()){
                            Categorymodel model=snapshot.toObject(Categorymodel.class);
                            model.setCatid(snapshot.getId());
                            categories.add(model);
                        }
                        adpter.notifyDataSetChanged();

                    }
                });

        binding.categorylist.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.categorylist.setAdapter(adpter);
        binding.spinwheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),SpinnerActivity.class));
            }
        });
        return binding.getRoot();
    }
}