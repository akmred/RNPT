package com.example.rnpt.fragments;

import android.annotation.SuppressLint;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnpt.App;
import com.example.rnpt.MainActivity;
import com.example.rnpt.R;
import com.example.rnpt.adapters.ListRNPTAdapter;
import com.example.rnpt.base.RNPTSource;
import com.example.rnpt.base.dao.RnptDao;
import com.example.rnpt.base.model.RNPT;
import com.example.rnpt.connection.authorization.AutorizationFNS;
import com.example.rnpt.connection.authorization.CenterConnectionAutorization;
import com.example.rnpt.connection.authorization.ConvertDataAnswerCenterConnection;
import com.example.rnpt.connection.authorization.DataAuthorization;
import com.example.rnpt.fragments.dialogfragment.DialogEditRnpt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment_list_rnpt extends Fragment {

    private ListRNPTAdapter adapter;
    Activity activity;
    AutorizationFNS autorizationFNS;
    private RNPTSource rnptSource;

    public Fragment_list_rnpt() {

    }

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

        RnptDao rnptDao = App
                .getInstance()
                .getRnptDao();

        rnptSource = new RNPTSource(rnptDao);

        adapter = new ListRNPTAdapter(rnptSource, activity, requireActivity());
        recycler_list_rnpt.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        OnClickListener(fab);

        autorizationFNS = new AutorizationFNS();

    }

    private void OnClickListener(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //adapter.addItem("new RNPT");
                // Вызываем диалог с новым добавлением РНПТ
                DialogEditRnpt dialogEditRnpt = new DialogEditRnpt(0);
                dialogEditRnpt.show(getFragmentManager(), "DialogCustom");

            }
        });
    }


    private void initData() {

        if (rnptSource.getCountRnpts() == 0) {

            for (int i = 0; i < 7; i++) {

                RNPT rnpt = new RNPT();
                rnpt.NameRnpt = String.format("EMUL2030/260917/0080123/0%d", i);
                rnpt.DataReceived = false;

                rnptSource.addRNPT(rnpt);

            }

        }

    }

    private void setTestingData() {

        initData();
    }

    private void fillInDataOnForm(View view) {
    }

    public void onDialogResultNewElement(String edit_rnpt, String ok) {

        adapter.addItem(edit_rnpt);

    }
}
