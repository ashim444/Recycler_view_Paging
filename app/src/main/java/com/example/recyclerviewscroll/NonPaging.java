package com.example.recyclerviewscroll;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NonPaging extends AppCompatActivity {
    private List<Assessment> assessments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging_views);
        RecyclerView rvContacts = findViewById(R.id.rvContact);
        createContactsList(2000000);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvContacts.setLayoutManager(linearLayoutManager);
        AssessmentAdapter adapter = new AssessmentAdapter(assessments);
        rvContacts.setAdapter(adapter);
    }

    public void createContactsList(int numContacts) {
        assessments = new ArrayList<>();
        for (int i = 0; i <= numContacts; i++) {

            assessments.add(new Assessment("Assessment Number " + ++i));
        }

    }
}
