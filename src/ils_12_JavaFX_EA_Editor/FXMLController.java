package ils_12_JavaFX_EA_Editor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLController {

	
	//TextArea als Instanzvariable
	@FXML private TextArea textFeld;

	//Instanzvariable für die Bühne
	private Stage meineStage;

	
	//------------------------------ Methode für eine neue Datei -------------------------------------------
	
	@FXML protected void neuKlick (ActionEvent event) {
		//den Dialog erzeugen und anzeigen
		Alert meinDialog = new Alert(AlertType.INFORMATION, "Die Daten werden gelöscht.");
		//den Text setzen
		meinDialog.setHeaderText("Bitte beachten");
		//den Dialog anzeigen
		meinDialog.showAndWait();
		//Textfeld leeren, wenn der User auf OK geklickt hat
	    if (meinDialog.getResult() == ButtonType.OK) {
	        // das Textfeld leeren
	        textFeld.clear();
	    }
	}

	
	//------------------------------ Methode für das Öffnen einer vorhandenen Datei ----------------------------------------
	
	@FXML protected void ladenKlick (ActionEvent event) {
		//eine neue Instanz der Klasse FileChooser
		FileChooser oeffnenDialog = new FileChooser();
		//den Titel für das Dialog-Fenster setzen
		oeffnenDialog.setTitle("Datei oeffnen");
		//im Dialog soll gleich der Benutzerordner angezeigt werden und nicht der oberste Ordner (Standard)
		//user.home steht dabei für den Benutzerordner
		oeffnenDialog.setInitialDirectory(new File(System.getProperty("user.home")));
		//den Filter setzen
		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("Textdateien", "*.txt"));
		//den Öffnen-Dialog anzeigen und das Ergebnis beschaffen (oder null statt meineStage, um den Dialog in der Mitte des Bildschirms anzuzeigen)
		File datei = oeffnenDialog.showOpenDialog(meineStage);
		//wenn eine Datei ausgewählt wurde
		if(datei != null)
			//dann den Inhalt über eine eigene Methode einlesen
			textFeld.setText(datenLesen(datei));
	}


	//------------------------------ Methode zum Laden der ausgewählten Datei -------------------------------------------
	
	//Datei die gelesen werden soll wird übergeben
	//zurückgegeben wird eine Zeichenkette mit dem Inhalt
	@FXML private String datenLesen (File dateiLesen) {
		//gelesen wird als int
		int zeichen = 0;
		//mit dem Objekt vom Typ StringBuilder kann man beim Lesen immer ein Zeichen hinzufügen. (bei einem normalen String müsste man jedes Mal den ganzen String neu erstellen)
		StringBuilder text = new StringBuilder();
		//eine Instanz der Klasse FileReader mit der Datei erzeugen
		try (FileReader tempDatei = new FileReader (dateiLesen)) {
			//solange das Ende der Datei nicht erreicht ist, werden die Zeichen einzeln gelesen und an den StringBuilder angehängt
			zeichen = tempDatei.read();
			while (zeichen != -1) {
				//Zeichen wird in den Typ char umgewandelt
				text.append((char) zeichen);
				zeichen = tempDatei.read();
			}
		}
		catch (IOException e) {
			//System.out.println("Beim Laden ist ein Problem aufgetreten");
			//Ausgabe stattdessen über einen Dialog:
			Alert meinDialog1 = new Alert(AlertType.ERROR, "Beim Laden ist ein Problem aufgetreten.");
			//den Text setzen
			meinDialog1.setHeaderText("Bitte beachten");
			//den Dialog anzeigen
			meinDialog1.showAndWait();
		}
		return (text.toString());
	}
	

	//------------------------------ Methode zum Anzeigen des Speicher-Dialogs -------------------------------------------

	@FXML protected void speichernKlick(ActionEvent event) {
		//eine neue Instanz der Klasse FileChooser
		FileChooser speichernDialog = new FileChooser();
		//Titel für den Dialog
		speichernDialog.setTitle("Datei speichern");
		//den Ordner setzen
		speichernDialog.setInitialDirectory(new File(System.getProperty("user.home")));
		//Filter für den Datei-Typ
		speichernDialog.getExtensionFilters().add(new ExtensionFilter("Textdateien", "*.txt"));
		//Speichern-Dialog anzeigen und Ergebnis beschaffen (oder null statt meineStage, um den Dialog in der Mitte des Bildschirms anzuzeigen)
		File datei = speichernDialog.showSaveDialog(meineStage);
		//wenn eine Datei ausgewählt wurde
		if (datei != null) 
			//dann den Inhalt über eine eigene Methode speichern
			datenSchreiben(datei);
	}

	
	//------------------------------ Methode zum Schreiben beim Speichern ------------------------------------------------
	
	//die Methode bekommt die Datei übergeben, die geschrieben werden soll
	private void datenSchreiben(File dateiSchreiben) {
		//eine Instanz der Klasse FileWriter mit der Datei erzeugen
		try (FileWriter tempDatei = new FileWriter(dateiSchreiben)) {
			//Inhalt des Textfeldes wird direkt in die Datei geschrieben
			tempDatei.write(textFeld.getText());
		}
		catch (IOException e) {
			//System.out.println("Beim Schreiben ist ein Problem aufgetreten.");
			//Ausgabe stattdessen über einen Dialog:
			Alert meinDialog2 = new Alert(AlertType.ERROR, "Beim Schreiben ist ein Problem aufgetreten.");
			//den Text setzen
			meinDialog2.setHeaderText("Bitte beachten");
			//den Dialog anzeigen
			meinDialog2.showAndWait();
		}
	}

	
	//############################################## FUER EINSENDEAUFGABE 2 #########################################
	//------------------------------ Methode für den Dialog mit der Ja oder Nein Frage -------------------------------------------
	
	@FXML protected void frageDialog (ActionEvent event) {
		//den Dialog erzeugen und anzeigen
		Alert meinDialog3 = new Alert(AlertType.CONFIRMATION, "Mögen Sie Schokolade?", ButtonType.YES, ButtonType.NO);
		//Titel setzen
		meinDialog3.setTitle("Ja- oder Nein-Frage zur Einsendeaufgabe Nr. 2");
		//den Text setzen
		meinDialog3.setHeaderText("Bitte beantworten Sie die Frage");
		//den Dialog anzeigen
		meinDialog3.showAndWait();

		System.out.println(meinDialog3.getResult());

		if (meinDialog3.getResult() == ButtonType.YES) {
			
			//Titelleiste Beschriftung ändern
			meineStage.setTitle("JavaFX Editor - - - - - - - - - - für Schokoladenliebhaber :-)");
			
			//den Dialog erzeugen und anzeigen
			Alert schokoFan = new Alert(AlertType.INFORMATION, "Ich auch ;-)");
			//Titel setzen
			schokoFan.setTitle("Ja- oder Nein-Frage zur Einsendeaufgabe Nr. 2");
			//den Text setzen
			schokoFan.setHeaderText("Sie mögen Schokolade.");
			//den Dialog anzeigen
			schokoFan.showAndWait();
		}
		else {
			
			//Titelleiste Beschriftung ändern
			meineStage.setTitle("JavaFX Editor - ganz ohne Schokolade");
			
			//den Dialog erzeugen und anzeigen
			Alert keinSchokoFan = new Alert(AlertType.INFORMATION, "Das ist nicht so schlimm.");
			//Titel setzen
			keinSchokoFan.setTitle("Ja- oder Nein-Frage zur Einsendeaufgabe Nr. 2");
			//den Text setzen
			keinSchokoFan.setHeaderText("Sie mögen keine Schokolade.");
			//den Dialog anzeigen
			keinSchokoFan.showAndWait();
		}
	}
	
	
	//MEINS WAR FALSCH, WEIL NICHT EINE EIGENE KLASSE, DIE VON ALERT ABGELEITET IST.
	//so hat es der Lehrer geschickt:
	
//	import javafx.scene.control.Alert;
//	import javafx.scene.control.ButtonType;
//	 
//	public class JavaFX_JaNeinDialog extends Alert{
//	 
//		boolean rueckgabe;
//		 
//		//der Konstruktor
//		public JavaFX_JaNeinDialog(String titel, String ueberschrift,
//		String meldung) {
//			super(AlertType.CONFIRMATION, meldung, ButtonType.YES,
//			ButtonType.NO);
//			//die Texte setzen
//			setTitle(titel);
//			setHeaderText(ueberschrift);
//		}
//	 
//		//den Dialog anzeigen
//		public void dialogZeigen() {
//			//anzeigen und abwarten
//			showAndWait();
//			//die Rückgabe verarbeiten
//			if (getResult() == ButtonType.YES)
//				rueckgabe = true;
//			else
//				rueckgabe = false;
//		}
//	 
//		//den Wert liefern
//		public boolean wertHolen() {
//			return rueckgabe;
//		}
//	}

	
	
	
	
	
	
	
	
	
	
	
	

	//############################################## FUER EINSENDEAUFGABE 3 #########################################
	//------------------------------ Methode für das Ausschneiden eines Textabschnitts und Speichern in der Zwischenablage -------------------------

	@FXML protected void ausschneiden (ActionEvent event) {
		//den Textabschnitt ausschneiden
		textFeld.cut();
	}

	
	//------------------------------ Methode für das Kopieren eines Textabschnitts und Speichern in der Zwischenablage -----------------------------
	
	@FXML protected void kopieren (ActionEvent event) {
		//den Textabschnitt kopieren
		textFeld.copy();
	}

	
	//------------------------------ Methode für das Einfuegen eines Textabschnitts ----------------------------------------------------------
	
	@FXML protected void einfuegen (ActionEvent event) {
		//den Textabschnitt einfuegen
		textFeld.paste();
	}		

	
	//------------------------------ Methode zum Beenden -------------------------------------------------------------------------------------

	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}

	
	//------------------------------ Methode setzt die Bühne auf den übergebenen Wert --------------------------------------------------------

	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
	}

}
