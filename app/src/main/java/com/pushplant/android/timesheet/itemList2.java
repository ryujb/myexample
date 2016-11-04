package com.pushplant.android.timesheet;

/**
 * Created by Administrator on 2016-10-28.
 */

public class itemList2 {

    public String mImg;
    public String mTitle;
    public String mContent;
    public String mId;
    public String mContent2;

    public itemList2() {

    }

    public itemList2(String mImg, String mTitle, String mContent,String mId,String mContent2) {
        this.mImg = mImg;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mId = mId ;
        this.mContent2 = mContent2;
    }

    public String getmImg() {
        return mImg;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmContent2() {
        return mContent2;
    }

    public void setmContent2(String mContent2) {
        this.mContent2 = mContent2;
    }
}
