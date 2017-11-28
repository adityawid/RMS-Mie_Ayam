package com.kurnniawan.pelayanmanajemenlayananrestaurant;

import java.util.Map;

/**
 * Created by user on 13/11/2017.
 */

public class InputConstructor {

    String idMeja;
    String namaPemesan;
    String keteranganPemesan;
    //String menuPesanan;
    String namaPelayan;
    long mTimestamp;
    Map<String,Object> daftarPesanan;

    public InputConstructor() {

    }


    public InputConstructor(String idMeja, String namaPemesan, String keteranganPemesan, String namaPelayan, long mTimestamp, Map<String,Object> daftarPesanan) {
        this.idMeja = idMeja;
        this.namaPemesan = namaPemesan;
        this.keteranganPemesan = keteranganPemesan;
        this.namaPelayan = namaPelayan;
        this.mTimestamp =mTimestamp;
        this.daftarPesanan = daftarPesanan;
    }

    public String getIdMeja() {
        return idMeja;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public String getKeteranganPemesan() {
        return keteranganPemesan;
    }

    public String getNamaPelayan() {
        return namaPelayan;
    }

    public long getmTimestamp() {
        return mTimestamp;
    }

    public Map<String, Object> getDaftarPesanan() {
        return daftarPesanan;
    }
}
