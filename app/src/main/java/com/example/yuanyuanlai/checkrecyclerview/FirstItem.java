package com.example.yuanyuanlai.checkrecyclerview;

public class FirstItem {

    public FirstItem(String mTitle, boolean mIsChecked) {
        title = mTitle;
        isChecked = mIsChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean mChecked) {
        isChecked = mChecked;
    }

    private String title;
    private boolean isChecked;


}
