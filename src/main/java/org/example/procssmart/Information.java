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

    public Information(int stt, String noidung1, int sotien, String ngay, String ghichu) {
        this.stt = 0;
        this.noidung1 = "";
        this.sotien = 0;
        this.ngay = "";
        this.ghichu = "";
    }
}
