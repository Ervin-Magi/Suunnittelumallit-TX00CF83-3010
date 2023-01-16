package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Author_ Valtteri MÃ¤ntymÃ¤ki

public class Henkilo {
    
    private final StringProperty nimi;
    private final IntegerProperty aika;
    
    public Henkilo(String nimi, int aika) {
        this.nimi = new SimpleStringProperty(nimi);
        this.aika = new SimpleIntegerProperty(aika);
    }
    
    public StringProperty nimiProperty() {
        return nimi;
    }
    
    public IntegerProperty aikaProperty() {
        return aika;
    }
    
    public void setAika(int aika) {
        this.aika.set(aika);
    }
    
    public void setNimi(String nimi) {
        this.nimi.set(nimi);
    }

    public String getNimi() {
        return nimi.get();
    }

    public int getAika() {
        return aika.get();
    }

    
    
}