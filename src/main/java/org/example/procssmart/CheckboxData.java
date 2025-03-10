package org.example.procssmart;

import java.io.Serializable;

public class CheckboxData implements Serializable {

    private int hang;
    private int cot;
    private boolean checked;

    public CheckboxData(int hang, int cot, boolean checked) {
        this.hang = hang;
        this.cot = cot;
        this.checked = checked;
    }

    public CheckboxData() {

    }

    public int getHang() {
        return hang;
    }
    public void setHang(int hang) {
        this.hang = hang;
    }
    public int getCot() {
        return cot;
    }
    public void setCot(int cot) {
        this.cot = cot;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "CheckboxStatus{" +
                "hang=" + hang +
                ", cot=" + cot +
                ", checked=" + checked +
                '}';
    }
}
