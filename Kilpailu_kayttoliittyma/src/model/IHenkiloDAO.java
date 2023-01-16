package model;

import javafx.collections.ObservableList;



public interface IHenkiloDAO {
    public abstract boolean createHenkilo(Henkilo henkilo);
    public abstract ObservableList<Henkilo> readHenkilot();
    public abstract Henkilo readHenkilo();
}