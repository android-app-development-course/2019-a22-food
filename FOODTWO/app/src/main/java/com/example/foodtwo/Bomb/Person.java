package com.example.foodtwo.Bomb;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class Person extends BmobUser {
    private BmobFile touxiang=null;


    public BmobFile getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(BmobFile touxiang) {
        this.touxiang = touxiang;
    }
}