package com.example.foodtwo.Bomb;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;

public class PostLike extends BmobObject {
    private String text;
    private String tag;
    private BmobFile picture;
    private Person Author;//作者

    private BmobPointer pointer;

    public BmobPointer getPointer() {
        return pointer;
    }

    public void setPointer(BmobPointer pointer) {
        this.pointer = pointer;
    }

    public Person getAuthor() {
        return Author;
    }

    public void setAuthor(Person Author) {
        this.Author = Author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public BmobFile getPicture() {
        return picture;
    }

    public void setPicture(BmobFile picture) {
        this.picture = picture;
    }
}
