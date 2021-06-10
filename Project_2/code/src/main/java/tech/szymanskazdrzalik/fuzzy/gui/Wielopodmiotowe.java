package tech.szymanskazdrzalik.fuzzy.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import tech.szymanskazdrzalik.fuzzy.model.Wypadek;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.Kwantyfikator;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.WielopodmiotowePodsumowanieLingwistyczne;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.ZmiennaLingwistyczna;
import tech.szymanskazdrzalik.fuzzy.predefiniowany.PredefiniowaneKwalifikatorySumaryzatory;
import tech.szymanskazdrzalik.fuzzy.predefiniowany.PredefiniowaneKwantyfikatory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Wielopodmiotowe implements Initializable {
    public ComboBox<String> forma;
    public ComboBox<Integer> podmiot1;
    public ComboBox<Integer> podmiot2;
    public ComboBox<String> sumaryzatory;
    public Button dodaj;
    public ComboBox<String> sumaryzatoryWybrane;
    public Button usun;
    public Button dalej;
    public ComboBox<String> kwantyfikator;
    public ComboBox<String> kwalifikator;
    public Button dodajKwalifikator;
    public Button usunKwalifikator;
    public ComboBox<String> kwalifikatorWybrane;
    public Button podsumowaniePrzycisk;
    public Label labelKwantyfikator;
    public Label labelDostepneKwalifikatory;
    public Label labelWybraneKwalifikatory;

    private ObservableList<PodsumowanieLingwistyczneIMiary> podsumowanieLingwistyczneIMiaryObservableList;
    private List<Kwantyfikator> kwantyfikatorList;
    private List<ZmiennaLingwistyczna> kwalifikatoryList;
    private List<ZmiennaLingwistyczna> sumaryzatoryList;
    private ObservableList<String> sumaryzatoryListString;
    private ObservableList<String> kwalifikatoryString;
    private ObservableList<String> sumaryzatoryWybraneList;
    private ObservableList<String> kwalifikatorWybraneList;
    private List<Wypadek> podmioty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.kwantyfikatorList = PredefiniowaneKwantyfikatory.getKwantyfikatorList();
        this.kwalifikatoryList = PredefiniowaneKwalifikatorySumaryzatory.getAll();
        this.kwalifikatoryString = FXCollections.observableArrayList();
        this.kwalifikatorWybraneList = FXCollections.observableArrayList();
        this.kwalifikatorWybrane.setItems(this.kwalifikatorWybraneList);
        this.sumaryzatoryList = PredefiniowaneKwalifikatorySumaryzatory.getAll();
        this.sumaryzatoryListString = FXCollections.observableArrayList();
        this.sumaryzatoryWybraneList = FXCollections.observableArrayList();
        this.sumaryzatoryWybrane.setItems(this.sumaryzatoryWybraneList);

        this.setPodmioty();
        this.setForma();
        Wspolne.setKwantyfikator(this.kwantyfikatorList, this.kwantyfikator);
        Wspolne.setKwalifikator(this.kwalifikatoryString, this.kwalifikatoryList, this.kwalifikator);
        this.dodajKwalifikator.setOnAction(actionEvent -> Wspolne.setDodajKwalifikator(this.kwalifikator, this.kwalifikatoryString, this.kwalifikatorWybraneList, this.kwalifikatorWybrane));
        this.usunKwalifikator.setOnAction(actionEvent -> Wspolne.setUsunKwalifikator(this.kwalifikator, this.kwalifikatoryString, this.kwalifikatorWybraneList, this.kwalifikatorWybrane));
        Wspolne.setSumaryzatory(this.sumaryzatoryList, this.sumaryzatoryListString, this.sumaryzatory);
        this.dodaj.setOnAction(actionEvent -> Wspolne.setDodaj(this.sumaryzatoryWybraneList, this.sumaryzatoryListString, this.sumaryzatoryWybrane, this.sumaryzatory));
        this.usun.setOnAction(actionEvent -> Wspolne.setUsun(this.sumaryzatoryWybraneList, this.sumaryzatoryListString, this.sumaryzatoryWybrane, this.sumaryzatory));
        this.dalej.setOnAction(actionEvent -> this.setDalej());
    }

    private void setPodmioty(){
        List<Integer> podmiotyInt = new ArrayList<>();
        podmiotyInt.add(1);
        podmiotyInt.add(2);
        podmiotyInt.add(3);
        podmiotyInt.add(4);
        podmiot1.setItems(FXCollections.observableArrayList(podmiotyInt));
        podmiot2.setItems(FXCollections.observableArrayList(podmiotyInt));
        podmiot1.setValue(podmiotyInt.get(0));
        podmiot2.setValue(podmiotyInt.get(0));
    }

    private void setForma() {
        List<String> kwantyfikatoryString = new ArrayList<>();
        for (var e : WielopodmiotowePodsumowanieLingwistyczne.RodzajPodsumowania.values()) {
            kwantyfikatoryString.add(e.toString());
        }
        this.forma.setItems(FXCollections.observableArrayList(kwantyfikatoryString));
        this.forma.setValue(kwantyfikatoryString.get(0));
    }

    private void setDalej() {
        this.podsumowaniePrzycisk.setVisible(true);
        if (this.forma.getValue().equals(WielopodmiotowePodsumowanieLingwistyczne.RodzajPodsumowania.PIERWSZA_FORMA.toString())) {
            labelKwantyfikator.setVisible(true);
            kwantyfikator.setVisible(true);
            labelDostepneKwalifikatory.setVisible(false);
            kwalifikator.setVisible(false);
            dodajKwalifikator.setVisible(false);
            labelWybraneKwalifikatory.setVisible(false);
            kwalifikatorWybrane.setVisible(false);
            usunKwalifikator.setVisible(false);
        } else if (this.forma.getValue().equals(WielopodmiotowePodsumowanieLingwistyczne.RodzajPodsumowania.DRUGA_FORMA.toString())
                || this.forma.getValue().equals(WielopodmiotowePodsumowanieLingwistyczne.RodzajPodsumowania.TRZECIA_FORMA.toString())) {
            labelKwantyfikator.setVisible(true);
            kwantyfikator.setVisible(true);
            labelDostepneKwalifikatory.setVisible(true);
            kwalifikator.setVisible(true);
            dodajKwalifikator.setVisible(true);
            labelWybraneKwalifikatory.setVisible(true);
            kwalifikatorWybrane.setVisible(true);
            usunKwalifikator.setVisible(true);
        } else if (this.forma.getValue().equals(WielopodmiotowePodsumowanieLingwistyczne.RodzajPodsumowania.CZWARTA_FORMA.toString())) {
            labelKwantyfikator.setVisible(false);
            kwantyfikator.setVisible(false);
            labelDostepneKwalifikatory.setVisible(false);
            kwalifikator.setVisible(false);
            dodajKwalifikator.setVisible(false);
            labelWybraneKwalifikatory.setVisible(false);
            kwalifikatorWybrane.setVisible(false);
        }
    }


    public void podsumowanie(ActionEvent actionEvent) {
    }
}
