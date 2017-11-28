package com.kurnniawan.pelayanmanajemenlayananrestaurant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 13/11/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    Context context;
    String menu[];
    int[] menuDipilih;
    int [] jumlah;

    public ListAdapter(Context context, String[] menu) {
        this.context = context;
        this.menu = menu;
        menuDipilih = new int [menu.length];
        for(int i = 0; i< menuDipilih.length ; i++){
            menuDipilih[i] = 0;
        }
        jumlah = new int [menu.length];
        for(int i = 0; i<jumlah.length ; i++){
            jumlah[i] = 0;
        }
    }

    @Override
    public ListAdapter.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_menu, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListHolder holder, int position) {
        holder.rb.setText(menu[position]);
        if(this.menuDipilih[position] == 0){
            holder.rb.setChecked(false);
        }
        else {
            holder.rb.setChecked(true);
        }
        if(jumlah[position] == 0){
            holder.porsi.setText("");
        }else {
            holder.porsi.setText("" + jumlah[position]);
        }

    }
    public Map<String,Object> getPesanan(){
        Map<String,Object> pesanan = new HashMap<>();
        for(int i = 0; i< menu.length;i++){
            if(menuDipilih[i] != 0 && jumlah[i] != 0){
                pesanan.put(menu[i], jumlah[i]);
            }
        }
        return pesanan;
    }

    @Override
    public int getItemCount() {
        return menu.length;
    }

    public class ListHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener,TextWatcher {
        CheckBox rb;
        EditText porsi;
        public ListHolder(View itemView) {
            super(itemView);
            rb =  itemView.findViewById(R.id.rbMenu);
            rb.setOnCheckedChangeListener(this);

            porsi = (EditText) itemView.findViewById(R.id.etPorsi);
            porsi.addTextChangedListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            menuDipilih[getAdapterPosition()] = isChecked ? 1 : 0;
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int jumlah = 0;
            try{
                jumlah = Integer.parseInt(s.toString());
            }
            catch (NumberFormatException e ){

            }
            ListAdapter.this.jumlah[getAdapterPosition()] = jumlah;
        }
    }
}
