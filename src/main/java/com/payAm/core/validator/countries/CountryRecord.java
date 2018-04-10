package com.payAm.core.validator.countries;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Feb/07 - 09:42 PM
 */
public class CountryRecord {

    private String title;
    private String iso;
    private int callPrefix;
    private int digits;

    public CountryRecord(String title, String iso, int callPrefix, int digits) {
        this.title = title;
        this.iso = iso;
        this.callPrefix = callPrefix;
        this.digits = digits;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getIso() {
        return iso;
    }
    public void setIso(String iso) {
        this.iso = iso;
    }

    public int getCallPrefix() {
        return callPrefix;
    }
    public void setCallPrefix(int callPrefix) {
        this.callPrefix = callPrefix;
    }

    public int getDigits() {
        return digits;
    }
    public void setDigits(int digits) {
        this.digits = digits;
    }
}