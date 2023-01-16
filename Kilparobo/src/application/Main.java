package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.HenkiloAccessObject;
import view.MainWindowController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/*
 * Author_ Valtteri MÃ¤ntymÃ¤ki
 * 
 * Some parts of the code have been made with code.makery tutorials
 * link to site: https://code.makery.ch/library/javafx-tutorial/
 */


public class Main extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    static HenkiloAccessObject henkiloDAO = new HenkiloAccessObject();
	
	@Override
	public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Game start and highscore");
        
        initRootLayout();

        initMainWindow();
		
	}
	
	
	private void initRootLayout() {
		try {
            /* Load root layout from fxml file. */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            /* Show the scene containing the root layout. */
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	
	private void initMainWindow() {
		
		try {
            /* Load mainWindow overview. */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainOverview = (AnchorPane) loader.load();
            
            /* Set mainWindow overview into the center of root layout. */
            rootLayout.setCenter(mainOverview);
            
            /* Give the controller access of the main app */
            MainWindowController mController = loader.getController();
            mController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	

	public static void main(String[] args) {
		launch(args);
	}
}