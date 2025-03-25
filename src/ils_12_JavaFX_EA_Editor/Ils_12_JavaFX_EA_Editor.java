package ils_12_JavaFX_EA_Editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ils_12_JavaFX_EA_Editor extends Application {
	
	@Override
	public void start(Stage meineStage) throws Exception {
		
		//eine Instanz von FXMLLoader erzeugen und Verbindung zur FXML-Datei herstellen
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("sb_editor.fxml"));
		//die Datei laden
		Parent root = meinLoader.load();
		//der Controller wird explizit verbunden, um spezielle Interaktionen zu ermöglichen
		//dies ist insbesondere sinnvoll, wenn die Anwendung komplexer wird
		FXMLController meinController = meinLoader.getController();
		//und die Bühne übergeben
		meinController.setMeineStage(meineStage);

		//die Szene erzeugen
		//an den Konstruktor werden der oberste Knoten und die Groesse uebergeben
		Scene meineScene = new Scene(root, 400, 400);
		
		//den Titel ueber stage setzen
		meineStage.setTitle("JavaFX Editor Friederike Hemsen");
		//die Szene setzen
		meineStage.setScene(meineScene);
		
		//gleich zum Start maximieren
		meineStage.setMaximized(true);
		
		//oder zum Start im Vollbildmodus mit:
		//meineStage.setFullScreen(true);
		
		//und anzeigen
		meineStage.show();
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}

}
