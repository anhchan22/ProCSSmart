package org.example.procssmart;

public class Information {

    private int stt;
    private String hoten;
    private String noidung1;
    private int sotien;
    private String ngay;
    private String ghichu;

    public int getStt() {
        return stt;
    }

    public String getHoten() {
        return hoten;
    }

    public String getNoidung1() {
        return noidung1;
    }

    public int getSotien() {
        return sotien;
    }

    public String getNgay() {
        return ngay;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setNoidung1(String noidung1) {
        this.noidung1 = noidung1;
    }
    public void setSotien(int sotien) {
        this.sotien = sotien;
    }
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }


    public Information(int stt, String noidung1, int sotien, String ngay, String ghichu) {
        this.stt = stt;
        this.noidung1 = noidung1;
        this.sotien = sotien;
        this.ngay = ngay;
        this.ghichu = ghichu;
    }

    public Information() {

    }
}
