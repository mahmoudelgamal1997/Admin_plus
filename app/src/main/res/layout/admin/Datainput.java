package com.example2017.android.admin;

/**
 * Created by M7moud on 13-Dec-17.
 * to  retrieve catorgy data
 */
public class Datainput {

    private String catorgy_name;

    public Datainput() {
    }

    public Datainput(String catorgy_name) {
        this.catorgy_name = catorgy_name;
    }

    public String getCatorgy_name() {
        return catorgy_name;
    }

    public void setCatorgy_name(String catorgy_name) {
        this.catorgy_name = catorgy_name;
    }

}
