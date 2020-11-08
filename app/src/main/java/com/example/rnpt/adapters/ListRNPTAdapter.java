package com.example.rnpt.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnpt.R;
import com.example.rnpt.fragments.FragmentOpenRNPT;
import com.example.rnpt.fragments.Fragment_list_rnpt;
import com.example.rnpt.fragments.Fragment_settings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ListRNPTAdapter extends RecyclerView.Adapter<ListRNPTAdapter.ViewHolder>{

    private List<String> data;
    private Activity activity;
    private int menuPosition;
    private int selectedPosition = -1;
    TextView textElement;

    public ListRNPTAdapter(List<String> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new ViewHolder(view);
    }

    public TextView getTextElement(){
        return textElement;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        textElement = holder.getTextElement();
        textElement.setText(data.get(position));

        setOnItemClickBehavior(textElement, position);
        highligthSelectedPosition(textElement, position);

//        textElement.setOnLongClickListener((view)-> {
//            menuPosition = position;
//            return false;
//        });
//
//
//        if (activity != null){
//            activity.registerForContextMenu(textElement);
//        }
    }


    private void highligthSelectedPosition(TextView textElement, int position) {
        if(position == selectedPosition){
            int color = ContextCompat.getColor(activity, R.color.teal_200);
            textElement.setBackgroundColor(color);
        }else {
            int color = ContextCompat.getColor(activity, android.R.color.transparent);
            textElement.setBackgroundColor(color);
        }
    }

    private void setOnItemClickBehavior(TextView textElement, int position) {
        textElement.setOnClickListener((v) -> {
            selectedPosition = position;
            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public void addItem(String element){
        data.add(element);
        notifyItemInserted(data.size()-1);
    }


    void removeItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    void clearItems(){
        data.clear();
        notifyDataSetChanged();
    }


    // класс ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textElement;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textElement = itemView.findViewById(R.id.textElement);
        }
        public TextView getTextElement() {return textElement; }
    }

}
