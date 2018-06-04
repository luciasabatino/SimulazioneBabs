package it.polito.tdp.babs;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.*;

import it.polito.tdp.babs.model.CountResult;
import it.polito.tdp.babs.model.Model;
import it.polito.tdp.babs.model.SimulationResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class BabsController {

	private Model model;

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private DatePicker pickData;

	@FXML
	private Slider sliderK;

	@FXML
	private TextArea txtResult;

	@FXML
	void doContaTrip(ActionEvent event) {
		try {
		txtResult.clear();
		LocalDate date = pickData.getValue();
		List<CountResult> res = model.getTripCounts(date);
		if(res==null) {
			txtResult.setText("Non ci sono trip per la data selezionata");
			return;
		}
		txtResult.appendText("Punto 1: \n");
		for(CountResult cc : res) {
			txtResult.appendText(cc.toString());
		}
		}
		catch(RuntimeException e) {
			txtResult.setText("Errore di connessione al DB");
		}
	}

	@FXML
	void doSimula(ActionEvent event) {
		try {
			txtResult.clear();
			LocalDate date = pickData.getValue();
			if(date.getDayOfWeek().getValue()>4) {
				txtResult.setText("Selezionare un giorno dal lunedi al venerdi");
				return;
			}
			
			Double k = sliderK.getValue();
			SimulationResult res = model.simula(date,k);
			txtResult.appendText(res.toString());
		}catch(RuntimeException e) {
			txtResult.setText("Errore di connessione al DB");
		}
	}

	@FXML
	void initialize() {
		assert pickData != null : "fx:id=\"pickData\" was not injected: check your FXML file 'Babs.fxml'.";
		assert sliderK != null : "fx:id=\"sliderK\" was not injected: check your FXML file 'Babs.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Babs.fxml'.";

		pickData.setValue(LocalDate.of(2013, 9, 1));
	}
}
