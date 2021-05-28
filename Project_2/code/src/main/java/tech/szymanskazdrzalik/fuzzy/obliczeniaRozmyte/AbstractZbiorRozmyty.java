package tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte;

import java.util.Map;

public abstract class AbstractZbiorRozmyty implements ZbiorRozmyty {

    private final Double poczatekPrzestrzeniRozwazan;
    private final Double koniecPrzestrzeniRozwazan;

    public AbstractZbiorRozmyty(Double poczatekPrzestrzeniRozwarzan, Double koniecPrzestrzeniRozwarzan) {
        this.poczatekPrzestrzeniRozwazan = poczatekPrzestrzeniRozwarzan;
        this.koniecPrzestrzeniRozwazan = koniecPrzestrzeniRozwarzan;
    }

    public <T> boolean jestPusty() {
        return this.liczbaKardynalna(objectDoubleMap) == 0;
    }

    public <T> boolean jestNormalny(Map<T, Double> objectDoubleMap) {
        return true;
    }

    public Double getPoczatekPrzestrzeniRozwazan() {
        return poczatekPrzestrzeniRozwazan;
    }

    public Double getKoniecPrzestrzeniRozwazan() {
        return koniecPrzestrzeniRozwazan;
    }

    public ZbiorRozmyty iloczynZbiorow(AbstractZbiorRozmyty zbiorRozmyty) {
        return new AbstractZbiorRozmyty(
                Math.min(this.poczatekPrzestrzeniRozwazan, zbiorRozmyty.poczatekPrzestrzeniRozwazan),
                Math.max(this.koniecPrzestrzeniRozwazan, zbiorRozmyty.koniecPrzestrzeniRozwazan)) {
            @Override
            public Double przynaleznosc(Double x) {
                return Math.min(AbstractZbiorRozmyty.this.przynaleznosc(x), zbiorRozmyty.przynaleznosc(x));
            }
        };
    }


    public ZbiorRozmyty sumaZbiorow(AbstractZbiorRozmyty zbiorRozmyty) {
        return new AbstractZbiorRozmyty(
                Math.min(this.poczatekPrzestrzeniRozwazan, zbiorRozmyty.poczatekPrzestrzeniRozwazan),
                Math.max(this.koniecPrzestrzeniRozwazan, zbiorRozmyty.koniecPrzestrzeniRozwazan)) {
            @Override
            public Double przynaleznosc(Double x) {
                return Math.max(AbstractZbiorRozmyty.this.przynaleznosc(x), zbiorRozmyty.przynaleznosc(x));
            }
        };
    }

    public ZbiorRozmyty dopelnienieZbioru() {
        return new AbstractZbiorRozmyty(this.poczatekPrzestrzeniRozwazan, this.koniecPrzestrzeniRozwazan) {
            @Override
            public Double przynaleznosc(Double x) {
                return 1 - AbstractZbiorRozmyty.this.przynaleznosc(x);
            }
        };
    }
}
