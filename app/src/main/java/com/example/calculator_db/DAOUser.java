package com.example.calculator_db;

import android.hardware.lights.LightState;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class DAOUser {
    private DatabaseReference databaseReference;
    private String pricol;
    private int pricol2;

    public DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    public DAOUser(FirebaseUser currentFirebaseUser) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName() + "/" + currentFirebaseUser.getUid());
    }

    public Task<Void> add(User usr, String id) {
        return databaseReference.child(id).setValue(usr);
    }

    public void addPart(Part part) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pricol = snapshot.getValue().toString();
                String[] separated = pricol.split(", ");
                pricol2 = separated.length;
                databaseReference.child(String.valueOf(pricol2)).setValue(part.getRequest() + "=" + part.getAnswer());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
