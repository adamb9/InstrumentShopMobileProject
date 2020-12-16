//Adam Baldwin
//R00176025
//SDH3A

//Instrument item class
package com.example.mobiledevicesproject2;

public class Item_Instrument {
    int instrumentImage;
    String name;
    String price;
    String id;

    public Item_Instrument(String name, String price, String id) {
        this.name=name;
        this.price=price;
        this.id=id;
    }

    public Item_Instrument(){

    }

    public int getInstrumentImage() {
        return instrumentImage;
    }

    public void setInstrumentImage(int instrumentImage) {
        this.instrumentImage = instrumentImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
