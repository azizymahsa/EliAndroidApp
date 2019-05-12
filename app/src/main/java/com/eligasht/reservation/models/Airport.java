package com.eligasht.reservation.models;

public class Airport {
    private String Tesxt;
    private String TextFa;
    private String ShortDes;
    private String Value;
    private String LongDes;
    private String Tag;
    private String Icon;
    private String IconDown;
    private boolean IsSelectable;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public boolean isSelectable() {
        return IsSelectable;
    }

    public void setSelectable(boolean selectable) {
        IsSelectable = selectable;
    }

    public String getTesxt() {
        return Tesxt;
    }

    public void setTesxt(String tesxt) {
        Tesxt = tesxt;
    }

    public String getTextFa() {
        return TextFa;
    }

    public void setTextFa(String textFa) {
        TextFa = textFa;
    }

    public String getShortDes() {
        return ShortDes;
    }

    public void setShortDes(String shortDes) {
        ShortDes = shortDes;
    }

    public String getLongDes() {
        return LongDes;
    }

    public void setLongDes(String longDes) {
        LongDes = longDes;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getIconDown() {
        return IconDown;
    }

    public void setIconDown(String iconDown) {
        IconDown = iconDown;
    }
}
