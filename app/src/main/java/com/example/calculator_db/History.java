package com.example.calculator_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    public static ArrayList<Part> partList = new ArrayList<Part>();
    private ListView listView;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference(User.class.getSimpleName() + "/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setupData();
        setUpList();
    }

    private void setupData() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                partList.clear();
                String pricol = snapshot.getValue().toString();
                System.out.println(pricol);
                String[] separated1 = pricol.split(", ");
                if (separated1.length == 1)
                    return;
                for (int i = separated1.length - 1; i >= 0; i--) {
                    String[] separated2 = separated1[i].split("=");
                    if (separated2[0].equals("email")) {
                        continue;
                    }
                    if (separated2[2].contains("}")) {
                        separated2[2] = separated2[2].substring(0, separated2[2].length()-1);
                    }
                    Part request = new Part(separated2[1], separated2[2]);
                    partList.add(request);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpList() {
        listView = (ListView) findViewById(R.id.partListView);
        PartAdapter adapter = new PartAdapter(getApplicationContext(),0, partList);
        listView.setAdapter(adapter);
    }
}