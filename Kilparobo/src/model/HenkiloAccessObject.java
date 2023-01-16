package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*Author_ Valtteri MÃ¤ntymÃ¤ki */

public class HenkiloAccessObject implements IHenkiloDAO {
    
    private Connection conn;
    
    /* Kun AO otetaan kÃ¤yttÃ¶Ã¶n muodostaa yhteyden tietokantaan */
    public HenkiloAccessObject() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/kilpa_ajo", "olso", "olso");
            System.out.println("Yhteyden modostaminen onnistui!");
        } catch (Exception e) {
            System.out.println("Yheyden otto tietokantaan epÃ¤onnistui!");
            System.exit(-1);
        }
    }

    
    /* LisÃ¤tÃ¤Ã¤n tietokantaan henkilÃ¶ objecti Nimi ja aika */
    @Override
    public boolean createHenkilo(Henkilo henkilo) {

        try {
            
            String query = "INSERT INTO ajat (nimi, aika) VALUES (?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, henkilo.getNimi());
            ps.setInt(2, henkilo.getAika());
            ps.execute();
            
            /* Palautetaan tosi jos kaikki suoritukset ok */
            return true;
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return false;
    }

    /* Luetaan tietokannan taulusta ajat kaikki henkilÃ¶t */
    @Override
    public ObservableList<Henkilo> readHenkilot() {

        ArrayList<Henkilo> henkilot = new ArrayList<>();
        
        try (PreparedStatement myStatement = 
                conn.prepareStatement("SELECT * FROM ajat ORDER BY aika ASC")){
            
            /* Suoritetaan kysely ja otetaan arvo talteen */
            ResultSet myResult = myStatement.executeQuery();
            
            while(myResult.next()) {
                String resultNimi = myResult.getString("nimi");
                int resultAika = myResult.getInt("aika");
                
                Henkilo henkilo = new Henkilo(resultNimi, resultAika);
                henkilot.add(henkilo);
            }
            
        } catch (SQLException e) {
            do {
                System.err.println(e.getMessage());
                System.err.println(e.getErrorCode());
                System.err.println(e.getSQLState());
            } while (e.getNextException() != null);
        }
        
        /* Luodaan palautettava array ja laitetaan Arrylistan arvo sen sisÃ¤Ã¤n */
        ObservableList<Henkilo> oListStavaka = FXCollections.observableArrayList(henkilot);
        return oListStavaka;
    }

    
    @Override
    public Henkilo readHenkilo() {
        return null;
    }
    
    
    /* Etsii tietokannasta nimeÃ¤ jos on palauttaa true jos ei sitten false */
    public boolean isNameAvailable(String nimi) {
        
        ArrayList<String> nimet = new ArrayList<>();
        
        try (PreparedStatement myStatement = 
                conn.prepareStatement("SELECT nimi FROM ajat")){
            
            ResultSet myResult = myStatement.executeQuery();
            
            while(myResult.next()) {
                nimet.add(myResult.getString("nimi"));
            }
            
            if(nimet.contains(nimi)) {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Jotain meni pieleen nimen hakemisessa");
        }
        
        return false;
    }

}