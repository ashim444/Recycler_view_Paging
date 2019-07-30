package com.example.recyclerviewscroll;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PagingView extends AppCompatActivity {

    int gapSize = 30;
    int listSize = 200000;
    private List<Assessment> assessments = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging_views);

        RecyclerView rvContacts = findViewById(R.id.rvContact);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvContacts.setLayoutManager(linearLayoutManager);
        final List<Assessment> allAssessments = new ArrayList<>();
        allAssessments.addAll(Objects.requireNonNull(addContactList(0)));
        final AssessmentAdapter adapter = new AssessmentAdapter(allAssessments);
        rvContacts.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                List<Assessment> newAssessment = addContactList(page);
                if (newAssessment == null) return;
                final int curSize = adapter.getItemCount();
                allAssessments.addAll(newAssessment);
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemRangeInserted(curSize, allAssessments.size() - 1);
                    }
                });
            }
        };
        rvContacts.addOnScrollListener(scrollListener);
    }

    public List<Assessment> addContactList(int paige) {
        int value = (gapSize * (paige + 1));
        int startPoint = paige * gapSize;
        if (listSize > value) {
            return createContactsList(startPoint, value);
        } else if (listSize > gapSize * paige) {
            return createContactsList(startPoint, listSize);
        } else return null;
    }

    public List<Assessment> createContactsList(int startPoint, int value) {

        for (int i = startPoint; i <= value; i++) {
            assessments.add(new Assessment("Assessment " + i));
        }
        return assessments.subList(startPoint, value);
    }
}
