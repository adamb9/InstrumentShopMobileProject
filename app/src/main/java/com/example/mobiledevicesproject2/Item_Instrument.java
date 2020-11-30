package com.example.mobiledevicesproject2;

public class Item_Instrument {
    int instrumentImage;
    String instrumentName;
    String instrumentPrice;

    public Item_Instrument(int instrumentImage, String instrumentName, String instrumentPrice) {
        this.instrumentImage = instrumentImage;
        this.instrumentName = instrumentName;
        this.instrumentPrice = instrumentPrice;
    }

    public int getInstrumentImage() {
        return instrumentImage;
    }

    public void setInstrumentImage(int instrumentImage) {
        this.instrumentImage = instrumentImage;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentPrice() {
        return instrumentPrice;
    }

    public void setInstrumentPrice(String instrumentPrice) {
        this.instrumentPrice = instrumentPrice;
    }
}
