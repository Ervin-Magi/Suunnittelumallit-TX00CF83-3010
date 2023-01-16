package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Henkilo;
import model.HenkiloAccessObject;
import model.RobotConnectionController;

/*Author_ Valtteri MÃ¤ntymÃ¤ki */

public class MainWindowController {
	
	@FXML
    private TableView<Henkilo> highscoreTable;
    @FXML
    private TableColumn<Henkilo, String> nameColumn;
    @FXML
    private TableColumn<Henkilo, Number> timeColumn;
    @FXML
    private TextField nimikentta;
	
	private Main maini;
	private HenkiloAccessObject HAO = new HenkiloAccessObject();
	private RobotConnectionController rcc = new RobotConnectionController();
	
	/* empty shell */
	public MainWindowController() {
	}
	
	
	@FXML
	private void initialize() {
		
		/* Initialize the person table with the two columns. */
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nimiProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().aikaProperty());
        
        /* Clear person details.!! this is a method to set data */
        showHighscoreTable(null);

        /* Listen for selection changes and show the person details when changed. */
        highscoreTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHighscoreTable(newValue));
	}
	
	
	private void showHighscoreTable(Henkilo henkilo) {	
	}
	

	/* Calling this to get referense back to main app */
	public void setMain(Main maini) {
		this.maini = maini;
		highscoreTable.setItems(HAO.readHenkilot());
	}
	
	
	/* Kun painetaan start buttonia */
	@FXML
	private void handelStart() {
		
		/* Tarkista onko syÃ¶tetty nimi jos on niin eteenpÃ¤in */
		if(nimikentta.getText().isEmpty() == false) {
		
			/* Tarkistetaan lÃ¶ytyykÃ¶ nimi jo tietokannasta */
			if(HAO.isNameAvailable(nimikentta.getText()) == false) {
			
				/* LÃ¤hetÃ¤ true robolle */
				rcc.lahetaTrue();
				System.out.println("True lÃ¤hetetty");
				
				/* Vastaan ota aika robolta */
				System.out.println("Odotetaan aikaa...");
				rcc.vastaanotaAika();
				
				System.out.println("Aika on long: " + rcc.palautaAika());
			
				/* Aseta nimi ja vastaan otettu aika henkilÃ¶ olioon */
				String nimi = nimikentta.getText();
				int aika = (int)rcc.palautaAika();
			
				/* lÃ¤hetÃ¤ henkilÃ¶ olio tietokantaan */
				HAO.createHenkilo(new Henkilo(nimi, aika));
				
				/* LisÃ¤tÃ¤Ã¤n henkilÃ¶ listaan */
				highscoreTable.getItems().add(new Henkilo(nimi, aika));
				
				/* jÃ¤rjestetÃ¤Ã¤n pÃ¶ytÃ¤ aika jÃ¤rjestykseen */
				highscoreTable.refresh();
			
				/* resettaa nimi kenttÃ¤ ja kantahenkilo */
				nimikentta.setText("");
			
			}else {
				/* TÃ¤hÃ¤n sellainen ilmoitus ikkuna ettÃ¤ nimi on jo olemassa! */
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Name is alredy in use");
	            alert.setHeaderText("Name in use error");
	            alert.setContentText("Please write a different name for the race...");
	            alert.show();
			}
			
		}else {
			/* TÃ¤hÃ¤n sellainen ilmoitus ikkuna ettÃ¤ nimi puttuu kentÃ¤stÃ¤! */
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("You didn't set the name");
            alert.setHeaderText("Name empty error");
            alert.setContentText("Please write your name for the race...");
            alert.show();
		}
		
	}
	
	

}