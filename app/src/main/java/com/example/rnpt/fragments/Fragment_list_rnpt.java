package com.example.rnpt.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.RadioAccessSpecifier;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnpt.MainActivity;
import com.example.rnpt.R;
import com.example.rnpt.adapters.ListRNPTAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment_list_rnpt extends Fragment {

    private ListRNPTAdapter adapter;
    Activity activity;

    public Fragment_list_rnpt(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_rnpt, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Инициализиуем переменные
        initializationVariable(view);
        // Заполняем реквизиты тестовыми данными
        setTestingData();
        // Заполняем форму
        fillInDataOnForm(view);


    }

    private void initializationVariable(View view) {

        RecyclerView recycler_list_rnpt = view.findViewById(R.id.recycler_list_rnpt);
        recycler_list_rnpt.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recycler_list_rnpt.setLayoutManager(layoutManager);

        adapter = new ListRNPTAdapter(initData(), activity);
        recycler_list_rnpt.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem("new RNPT");
            }
        });

    }


    private List<String> initData() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 7; i++){
            list.add(String.format("EMUL2030/260917/0080123/0%d", i));
        }

        return list;
    }

    private void setTestingData() {
    }

    private void fillInDataOnForm(View view) {
    }

}
