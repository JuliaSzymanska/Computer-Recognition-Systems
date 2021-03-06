package tech.szymanskazdrzalik.fuzzy.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tech.szymanskazdrzalik.fuzzy.Main;
import tech.szymanskazdrzalik.fuzzy.dao.AccidentDAO;
import tech.szymanskazdrzalik.fuzzy.dao.ResourcesAccidentDao;
import tech.szymanskazdrzalik.fuzzy.model.Wypadek;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.Etykieta;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.Kwantyfikator;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.PodsumowanieLingwistyczne;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.ZmiennaLingwistyczna;
import tech.szymanskazdrzalik.fuzzy.predefiniowany.PredefiniowaneKwalifikatorySumaryzatory;
import tech.szymanskazdrzalik.fuzzy.predefiniowany.PredefiniowaneKwantyfikatory;
import tech.szymanskazdrzalik.fuzzy.utils.PropertiesLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PodstawowyUzytkownik implements Initializable {

    @FXML
    public TableView<PodsumowanieLingwistyczneIMiary> podsumowanieTable;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnTekst;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT1;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT2;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT3;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT4;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT5;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT6;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT7;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT8;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT9;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT10;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnT11;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, String> columnSuma;
    @FXML
    public Button wagi;
    @FXML
    public TextField w1;
    @FXML
    public TextField w2;
    @FXML
    public TextField w3;
    @FXML
    public TextField w4;
    public TextField w5;
    @FXML
    public TextField w6;
    @FXML
    public TextField w7;
    @FXML
    public TextField w8;
    @FXML
    public TextField w9;
    @FXML
    public TextField w10;
    @FXML
    public TextField w11;
    @FXML
    public TableColumn<PodsumowanieLingwistyczneIMiary, Void> columnZapis;
    public Button dodajKwalifikator;
    public Button usunKwalifikator;
    @FXML
    private ComboBox<String> kwantyfikator;

    @FXML
    private ComboBox<String> kwalifikator;

    @FXML
    private ComboBox<String> kwalifikatorWybrane;

    @FXML
    private ComboBox<String> sumaryzatory;

    @FXML
    private ComboBox<String> sumaryzatoryWybrane;

    @FXML
    private Button dodaj;

    @FXML
    private Button usun;

    @FXML
    private Button akceptacja;

    @FXML
    private Label nieRowne;

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

        Wspolne.setKwantyfikator(this.kwantyfikatorList, this.kwantyfikator);
        Wspolne.setKwalifikator(this.kwalifikatoryString, this.kwalifikatoryList, this.kwalifikator);
        this.dodajKwalifikator.setOnAction(actionEvent -> Wspolne.setDodajKwalifikator(kwalifikator, kwalifikatoryString, kwalifikatorWybraneList, kwalifikatorWybrane));
        this.usunKwalifikator.setOnAction(actionEvent -> Wspolne.setUsunKwalifikator(kwalifikator, kwalifikatoryString, kwalifikatorWybraneList, kwalifikatorWybrane));
        Wspolne.setSumaryzatory(sumaryzatoryList, sumaryzatoryListString, sumaryzatory);
        this.akceptacja.setOnAction(actionEvent -> PodstawowyUzytkownik.this.podsumowanie());
        this.dodaj.setOnAction(actionEvent -> Wspolne.setDodaj(sumaryzatoryWybraneList, sumaryzatoryListString, sumaryzatoryWybrane, sumaryzatory));
        this.usun.setOnAction(actionEvent -> Wspolne.setUsun(sumaryzatoryWybraneList, sumaryzatoryListString, sumaryzatoryWybrane, sumaryzatory));

        this.setPoliczSumeWag();

        this.initTable();
        AccidentDAO accidentDao = new ResourcesAccidentDao();
        this.podmioty = new ArrayList<>();
        try {
            this.podmioty = accidentDao.getAll("Data/" + PropertiesLoader.getJsonName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
        this.podsumowanieLingwistyczneIMiaryObservableList = FXCollections.observableArrayList();
        this.columnTekst.setCellValueFactory(new PropertyValueFactory<>("tekst"));
        this.columnT1.setCellValueFactory(new PropertyValueFactory<>("T1"));
        this.columnT2.setCellValueFactory(new PropertyValueFactory<>("T2"));
        this.columnT3.setCellValueFactory(new PropertyValueFactory<>("T3"));
        this.columnT4.setCellValueFactory(new PropertyValueFactory<>("T4"));
        this.columnT5.setCellValueFactory(new PropertyValueFactory<>("T5"));
        this.columnT6.setCellValueFactory(new PropertyValueFactory<>("T6"));
        this.columnT7.setCellValueFactory(new PropertyValueFactory<>("T7"));
        this.columnT8.setCellValueFactory(new PropertyValueFactory<>("T8"));
        this.columnT9.setCellValueFactory(new PropertyValueFactory<>("T9"));
        this.columnT10.setCellValueFactory(new PropertyValueFactory<>("T10"));
        this.columnT11.setCellValueFactory(new PropertyValueFactory<>("T11"));
        this.columnSuma.setCellValueFactory(new PropertyValueFactory<>("glownaMiaraJakosci"));
        this.columnZapis.setCellValueFactory(new PropertyValueFactory<>("button"));
        this.podsumowanieTable.setItems(podsumowanieLingwistyczneIMiaryObservableList);
    }

    private void setPoliczSumeWag() {
        this.wagi.setOnAction(actionEvent -> {
            double T1waga = Double.parseDouble(this.w1.getText());
            double T2waga = Double.parseDouble(this.w2.getText());
            double T3waga = Double.parseDouble(this.w3.getText());
            double T4waga = Double.parseDouble(this.w4.getText());
            double T5waga = Double.parseDouble(this.w5.getText());
            double T6waga = Double.parseDouble(this.w6.getText());
            double T7waga = Double.parseDouble(this.w7.getText());
            double T8waga = Double.parseDouble(this.w8.getText());
            double T9waga = Double.parseDouble(this.w9.getText());
            double T10waga = Double.parseDouble(this.w10.getText());
            double T11waga = Double.parseDouble(this.w11.getText());
            double suma = T1waga +
                    T2waga +
                    T3waga +
                    T4waga +
                    T5waga +
                    T6waga +
                    T7waga +
                    T8waga +
                    T9waga +
                    T10waga +
                    T11waga;
            double eps = 0.0001;
            if (Math.abs(suma - 1.0) > eps) {
                this.nieRowne.setText("Suma warto??ci musi by?? r??wna 1");
            } else {
                this.nieRowne.setText("");
            }
            for (var i : this.podsumowanieLingwistyczneIMiaryObservableList) {
                i.calculateGlownaMiaraJakosci(new PodsumowanieLingwistyczneIMiary.MiaryJakosciWagi(T1waga, T2waga, T3waga, T4waga, T5waga, T6waga, T7waga, T8waga, T9waga, T10waga, T11waga));
            }
            this.podsumowanieTable.refresh();
        });
    }

    private void podsumowanie() {
        Kwantyfikator wybranyKwantyfikator = null;
        List<Etykieta<Wypadek>> wybranyKwalifikator = new ArrayList<>();
        List<Etykieta<Wypadek>> wybraneSumaryzatory = new ArrayList<>();
        String tempKwantyfiaktor = this.kwantyfikator.getSelectionModel().getSelectedItem();
        if (tempKwantyfiaktor.contains(Wspolne.ABSOLUTNY)) {
            tempKwantyfiaktor = tempKwantyfiaktor.substring(0, tempKwantyfiaktor.indexOf(Wspolne.ABSOLUTNY));
        } else {
            tempKwantyfiaktor = tempKwantyfiaktor.substring(0, tempKwantyfiaktor.indexOf(Wspolne.WZGLEDNY));
        }
        for (var e : this.kwantyfikatorList) {

            if (e.getEtykieta().getNazwa().equals(tempKwantyfiaktor)) {
                wybranyKwantyfikator = e;
                break;
            }
        }

        String tempKwalifikator = this.kwalifikator.getSelectionModel().getSelectedItem();
        for (var e : this.kwalifikatoryList) {
            for (var v : e.getEtykiety()) {
                for (var f : this.kwalifikatorWybraneList)
                    if (v.getNazwa().equals(f)) {
                        wybranyKwalifikator.add(v);
                        break;
                    }
            }
        }

        for (var e : this.sumaryzatoryList) {
            for (var v : e.getEtykiety()) {
                for (var f : this.sumaryzatoryWybraneList)
                    if (v.getNazwa().equals(f)) {
                        wybraneSumaryzatory.add(v);
                        break;
                    }
            }
        }
        PodsumowanieLingwistyczne podsumowanieLingwistyczne = new PodsumowanieLingwistyczne(wybranyKwantyfikator, this.podmioty, wybraneSumaryzatory, wybranyKwalifikator);
        PodsumowanieLingwistyczneIMiary podsumowanieLingwistyczneIMiary = new PodsumowanieLingwistyczneIMiary(podsumowanieLingwistyczne);
        this.podsumowanieLingwistyczneIMiaryObservableList.add(podsumowanieLingwistyczneIMiary);
    }

    public void doZaawansowanego(ActionEvent actionEvent) throws IOException {
        Main.setRoot("zaawansowany");
    }

    public void doWielopodmiotowego(ActionEvent actionEvent) throws IOException {
        Main.setRoot("wielopodmiotowe");
    }
}