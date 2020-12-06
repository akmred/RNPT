package com.example.rnpt.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnpt.R;
import com.example.rnpt.base.RNPTSource;
import com.example.rnpt.base.model.RNPT;
import com.example.rnpt.fragments.FragmentOpenRNPT;

import java.util.List;

public class ListRNPTAdapter extends RecyclerView.Adapter<ListRNPTAdapter.ViewHolder>{

    private Activity activity;
    private int menuPosition;
    private int selectedPosition = -1;
    TextView textElement;
    FragmentActivity context;
    private RNPTSource dataSource;

    public ListRNPTAdapter(RNPTSource dataSource, Activity activity, FragmentActivity context) {
        this.dataSource = dataSource;
        this.activity = activity;
        this.context = context;
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

        // Заполнение данными записи на экране
        List<RNPT> rnpts = dataSource.getRnpts();
        RNPT rnpt = rnpts.get(position);

        textElement = holder.getTextElement();
        textElement.setText(rnpt.NameRnpt);

        setOnItemClickBehavior(textElement, position);
        highligthSelectedPosition(textElement, position);

        setOnItemLongClickBehavior();

    }

    private void setOnItemLongClickBehavior() {
        textElement.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentOpenRNPT fragmentOpenRNPT = new FragmentOpenRNPT(dataSource.getRnpts().get(selectedPosition).NameRnpt);
                FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.context_main, fragmentOpenRNPT);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                return false;
            }
        });
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
        return dataSource.getCountRnpts();
    }

    public void addItem(String element){

        RNPT rnpt = new RNPT();
        rnpt.NameRnpt = element;
        rnpt.DataReceived = false;

        dataSource.addRNPT(rnpt);
        notifyItemInserted(dataSource.getCountRnpts()-1);
    }


//    void removeItem(int position){
//        data.remove(position);
//        notifyItemRemoved(position);
//    }

//    void clearItems(){
//        data.clear();
//        notifyDataSetChanged();
//    }


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
