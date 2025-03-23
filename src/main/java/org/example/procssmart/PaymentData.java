package org.example.procssmart;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class PaymentData implements Serializable {

    private String hoten;
    private String noidung1;
    private int sotien;
    private String ngay;
    private String ghichu;

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

    public String getFormattedSotien() {
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        return formatter.format(this.sotien);
    }

    public PaymentData( String noidung1, int sotien, String ngay, String ghichu) {
        this.noidung1 = noidung1;
        this.sotien = sotien;
        this.ngay = ngay;
        this.ghichu = ghichu;
    }

    public PaymentData() {

    }

    @Override
    public String toString() {
        return "Information{" +
                ", noidung1='" + noidung1 + '\'' +
                ", sotien=" + sotien +
                ", ngay='" + ngay + '\'' +
                ", ghichu='" + ghichu + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PaymentData that = (PaymentData) obj;
        return sotien == that.sotien &&
                Objects.equals(noidung1, that.noidung1) &&
                Objects.equals(ngay, that.ngay) &&
                Objects.equals(ghichu, that.ghichu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noidung1, sotien, ngay, ghichu);
    }
}
