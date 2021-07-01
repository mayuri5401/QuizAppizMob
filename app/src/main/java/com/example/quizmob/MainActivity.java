package com.example.quizmob;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizmob.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);

        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new Homefragment());
        transaction.commit();
        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                switch (i){
                    case 0:
                        transaction.replace(R.id.content,new Homefragment());
                        transaction.commit();

                        break;
                    case 1:
                        transaction.replace(R.id.content,new LeadboardFragment());
                        transaction.commit();

                        break;
                    case 2:
                        transaction.replace(R.id.content,new wallet());
                        transaction.commit();
                        break;
                    case 3:
                        transaction.replace(R.id.content,new profile());
                        transaction.commit();
                        break;

                }
                return false;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.wallet){
            Toast.makeText(this, "wallet is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}