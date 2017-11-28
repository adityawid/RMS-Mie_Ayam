package com.kurnniawan.pelayanmanajemenlayananrestaurant;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 13/11/2017.
 */

public class RiwayatList extends ArrayAdapter<InputConstructor> {

    private Activity context;
    private List<InputConstructor> inputConstructorList;

    public RiwayatList(Activity context, List<InputConstructor> inputConstructorList) {
        super(context, R.layout.list_riwayatpesanan, inputConstructorList);
        this.context = context;
        this.inputConstructorList = inputConstructorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View listItemView, @NonNull ViewGroup parent) {

        if(listItemView == null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_riwayatpesanan, parent, false);
        }

        TextView km = (TextView) listItemView.findViewById(R.id.tvRwtkodemeja); //kode meja
        TextView npel = (TextView) listItemView.findViewById(R.id.tvRwtnamapelayan); //nama pelayan
        TextView npem = (TextView) listItemView.findViewById(R.id.tvRwtnamapemesan); //nama pemesan
        TextView ket = (TextView) listItemView.findViewById(R.id.tvRwtketerangan); //ket menu
        TextView time = (TextView) listItemView.findViewById(R.id.tvWaktu);
        TextView pesanan = listItemView.findViewById(R.id.tvIsiPesanan);
        //RelativeTimeTextView wkt = listItemView.findViewById(R.id.timeStampPesan); //time

        InputConstructor inputConstructor = inputConstructorList.get(position);

        StringBuilder builder = new StringBuilder();
        Map<String,Object> daftarPesanan = inputConstructor.getDaftarPesanan();
        if(daftarPesanan != null){
            for(String key : daftarPesanan.keySet()){
                builder.append(key).append(" (").append(daftarPesanan.get(key)).append(")\n");
            }
        }
        else{
            builder.append("KOSONG");
        }

        pesanan.setText(builder.toString());
        km.setText(inputConstructor.getIdMeja());
        npel.setText(inputConstructor.getNamaPelayan());
        npem.setText(inputConstructor.getNamaPemesan());
        ket.setText(inputConstructor.getKeteranganPemesan());
        long Time = inputConstructor.getmTimestamp();
        Date date = new Date(Time);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        time.setText(sdf.format(date));

        return listItemView;
    }

}
