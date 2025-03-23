package org.example.procssmart;

import java.io.Serializable;

public class ThongkeData implements Serializable {
    private int row; //nguoi
    private int col;  //thang
    private String value;

    public ThongkeData(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
    public ThongkeData() {

    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ThongkeData{" +
                "row=" + row +
                ", col=" + col +
                ", value='" + value + '\'' +
                '}';
    }
}
