package tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte;

import java.util.ArrayList;
import java.util.List;

public class MiaryJakosci {

    /**
     * T1
     * To jest po prostu wartość funkcji przynależności obiektów dla kwalifikatora and z funckją przynależności zrobioną z andów funkcji przynależności tego sumaryzatora podzielonej przez funkcję przynależności obiektów dla kwalifikatora
     * A jak nie ma kwalifikatora to dzielimy przez liczbę obiektów
     * i na górze nie mnożymy
     * A jak jest kwantyfikator absolutny to nie dzielimy przez nic i robimy po prostu kwantyfikator od kardynalności dla andów funkcji przynależności sumaryzatora
     */
    public static Double stopienPrawdziwosci(PodsumowanieLingwistyczne podsumowanieLingwistyczne) throws NieBrakKwalifikatora {
        var x = Utils.iloczyn(podsumowanieLingwistyczne.getSumaryzator());
        if (podsumowanieLingwistyczne.getKwantyfikator().getJestAbsolutny()) {
            if (podsumowanieLingwistyczne.getKwalifikator().size() != 0) {
                x = x.iloczynZbiorow(podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty());
            }
//            System.out.println(x.liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty()));
//            System.out.println(podsumowanieLingwistyczne.getKwantyfikator()
//                    .getEtykieta()
//                    .getAbstractZbiorRozmyty()
//                    .przynaleznosc(x.liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty())));
            return podsumowanieLingwistyczne.getKwantyfikator()
                    .getEtykieta()
                    .getAbstractZbiorRozmyty()
                    .przynaleznosc(x.liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty()));
        }
        if (podsumowanieLingwistyczne.getKwalifikator().size() == 0) {
            return podsumowanieLingwistyczne.getKwantyfikator()
                    .getEtykieta()
                    .getAbstractZbiorRozmyty()
                    .przynaleznosc(
                            x.liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty())
                                    / podsumowanieLingwistyczne.getPodmioty().size());
        }
        x = x.iloczynZbiorow(podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty());
        return podsumowanieLingwistyczne.getKwantyfikator()
                .getEtykieta()
                .getAbstractZbiorRozmyty()
                .przynaleznosc(x.liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty())
                        / podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty().
                        liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty()));
    }


    /**
     * T1
     */
    public static Double stopienPrawdziwosci(WielopodmiotowePodsumowanieLingwistyczne wielopodmiotowePodsumowanieLingwistyczne) {
        var x = Utils.iloczyn(wielopodmiotowePodsumowanieLingwistyczne.getSumaryzator());
        double licznik, mianownik;
        switch (wielopodmiotowePodsumowanieLingwistyczne.getRodzajPodsumowania()) {
            case PIERWSZA_FORMA:
                licznik = ((1.0 / wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1().size()) *
                        x.liczbaKardynalna(wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1()));
                mianownik = licznik +
                        (1.0 / wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2().size()) *
                                x.liczbaKardynalna(wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2());
                return wielopodmiotowePodsumowanieLingwistyczne.getKwantyfikator().
                        getEtykieta().
                        getAbstractZbiorRozmyty().
                        przynaleznosc(licznik / mianownik);
            case DRUGA_FORMA:
                licznik = ((1.0 / wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1().size()) *
                        (x.iloczynZbiorow(wielopodmiotowePodsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty()))
                                .liczbaKardynalna(wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1()));
                mianownik = licznik +
                        (1.0 / wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2().size()) *
                                x.liczbaKardynalna(wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2());
                return wielopodmiotowePodsumowanieLingwistyczne.getKwantyfikator().
                        getEtykieta().
                        getAbstractZbiorRozmyty().
                        przynaleznosc(licznik / mianownik);
            case TRZECIA_FORMA:
                licznik = ((1.0 / wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1().size()) *
                        x.liczbaKardynalna(wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1()));
                mianownik = licznik +
                        ((1.0 / wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2().size()) *
                                x.iloczynZbiorow(wielopodmiotowePodsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty())
                                        .liczbaKardynalna(wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2()));
                return wielopodmiotowePodsumowanieLingwistyczne.getKwantyfikator().
                        getEtykieta().
                        getAbstractZbiorRozmyty().
                        przynaleznosc(licznik / mianownik);
            default:
                ImplikacjaRozmyta implikacjaRozmyta = new Reichenbach();

                var sum = 0.0;
                for (var i : wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1()) {
                    sum += implikacjaRozmyta.wartosc(x.przynaleznosc(i), 0);
                }
                for (var i : wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2()) {
                    sum += implikacjaRozmyta.wartosc(0, x.przynaleznosc(i));
                }

                return 1 - (sum) / (wielopodmiotowePodsumowanieLingwistyczne.getPodmioty1().size() + wielopodmiotowePodsumowanieLingwistyczne.getPodmioty2().size());

        }
    }

    /**
     * T2
     * Liczymy srednia kwadratowa ze stopni rozmycia
     */
    public static Double stopienNieperecyzyjnosci(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        double stopienPierwiastka = 1.0 / podsumowanieLingwistyczne.getSumaryzator().size();
        double iloczyn = 1;
        for (var x : podsumowanieLingwistyczne.getSumaryzator()) {
            iloczyn *= x.getAbstractZbiorRozmyty().stopienRozmycia(podsumowanieLingwistyczne.getPodmioty());
        }
        return 1.0 - Math.pow(iloczyn, stopienPierwiastka);
    }

    /**
     * T3
     */
    // TODO: 29.05.2021
    public static Double stopienPokrycia(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        var x = Utils.iloczyn(podsumowanieLingwistyczne.getSumaryzator());
        if (podsumowanieLingwistyczne.getKwalifikator().size() == 0) {
            return ((double) x.getNosnik(podsumowanieLingwistyczne.getPodmioty()).size()) / podsumowanieLingwistyczne.getPodmioty().size();
        }
        x = x.iloczynZbiorow(podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty());
        return ((double) x.getNosnik(podsumowanieLingwistyczne.getPodmioty()).size()) /
                podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty().getNosnik(podsumowanieLingwistyczne.getPodmioty()).size();
    }

    /**
     * T4
     */
    // TODO: 29.05.2021
    public static Double stopienTrafnosci(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        var x = stopienPokrycia(podsumowanieLingwistyczne);
        var iloczyn = 1;
        for (var y : podsumowanieLingwistyczne.getSumaryzator()) {
            iloczyn *= (double) y.getAbstractZbiorRozmyty().getNosnik(podsumowanieLingwistyczne.getPodmioty()).size();
            iloczyn /= podsumowanieLingwistyczne.getPodmioty().size();
        }
        return Math.abs(iloczyn - x);
    }


    /**
     * T5
     */

    public static Double dlugoscPodsumowania(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        return 2 * Math.pow(0.5, podsumowanieLingwistyczne.getSumaryzator().size());
    }

    /**
     * T6
     */
    // TODO: 29.05.2021
    public static Double stopienNieprecyzyjnosciKwantyfikatora(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        if (podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty() instanceof FunkcjaGausowska) {
            return 0.0;
        } else if (podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty() instanceof FunkcjaTrapezoidalna) {
            FunkcjaTrapezoidalna<Double> funkcjaTrapezoidalna = (FunkcjaTrapezoidalna<Double>) podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty();
            if (podsumowanieLingwistyczne.getKwantyfikator().getJestAbsolutny()) {
                return 1.0 - ((funkcjaTrapezoidalna.getKoniec() - funkcjaTrapezoidalna.getPoczatek()) / (podsumowanieLingwistyczne.getPodmioty().size()));
            } else {
                return 1.0 - (funkcjaTrapezoidalna.getKoniec() - funkcjaTrapezoidalna.getPoczatek());
            }
        }
        var z = Utils.iloczyn(podsumowanieLingwistyczne.getSumaryzator());
        int count = 0;
        for (var x : podsumowanieLingwistyczne.getPodmioty()) {
            if (podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty().przynaleznosc(z.przynaleznosc(x)) > 0) {
                count++;
            }
        }
        double cnt = count;
        double mian = podsumowanieLingwistyczne.getPodmioty().size();
        return 1 - (cnt / mian);

    }

    /**
     * T7
     */
    // TODO: 29.05.2021
    public static Double stopienKardynalnosciWzglednejKwantyfiaktora(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        var z = Utils.iloczyn(podsumowanieLingwistyczne.getSumaryzator());
        if (podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty() instanceof FunkcjaTrapezoidalna) {
            var x = (FunkcjaTrapezoidalna<Double>) podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty();
            var pole = (x.getPoczatekWartosciMaksymalnej() - x.getPoczatek()) * 0.5 + (x.getKoniecWartosciMaksymalnej() - x.getPoczatekWartosciMaksymalnej()) + (x.getKoniec() - x.getKoniecWartosciMaksymalnej()) * 0.5;
            return 1 - pole / podsumowanieLingwistyczne.getPodmioty().size();
        }
        List<Double> doubleList = new ArrayList<>();
        for (var x : podsumowanieLingwistyczne.getPodmioty()) {
            doubleList.add(z.przynaleznosc(x));
        }
        return 1 - (podsumowanieLingwistyczne.getKwantyfikator().getEtykieta().getAbstractZbiorRozmyty().liczbaKardynalna(doubleList) / doubleList.size());
    }

    /**
     * T8
     */
    public static Double stopienKardynalnosciWzglednejSumaryzatora(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        double stopienPierwiastka = 1.0 / podsumowanieLingwistyczne.getSumaryzator().size();
        double iloczyn = 1;
        for (var x : podsumowanieLingwistyczne.getSumaryzator()) {
            iloczyn *= x.getAbstractZbiorRozmyty().liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty());
            iloczyn /= podsumowanieLingwistyczne.getPodmioty().size();
        }
        return 1.0 - Math.pow(iloczyn, stopienPierwiastka);
    }

    /**
     * T9
     */
    public static Double stopienNieprecyzyjnosciKwalifikatora(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        if (podsumowanieLingwistyczne.getKwalifikator().size() == 0) {
            return 0.0;
        }
        return 1.0 - podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty().stopienRozmycia(podsumowanieLingwistyczne.getPodmioty());
    }

    /**
     * T10
     */
    public static Double stopienKardynalnosciWzglednejKwalifikatora(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        if (podsumowanieLingwistyczne.getKwalifikator().size() == 0) {
            return 0.0;
        }
        double iloczyn = podsumowanieLingwistyczne.getKwalifikatorZbiorRozmyty().liczbaKardynalna(podsumowanieLingwistyczne.getPodmioty());
        iloczyn /= podsumowanieLingwistyczne.getPodmioty().size();
        return 1.0 - iloczyn;
    }

    /**
     * T11
     */
    public static Double dlugoscKwalifikatora(PodsumowanieLingwistyczne podsumowanieLingwistyczne) {
        if (podsumowanieLingwistyczne.getKwalifikator().size() == 0) {
            return 2 * Math.pow(0.5, 1);
        }
        return 2 * Math.pow(0.5, podsumowanieLingwistyczne.getKwalifikator().size());
    }

    public static class NieBrakKwalifikatora extends Exception {
        public NieBrakKwalifikatora() {
        }

        public NieBrakKwalifikatora(String message) {
            super(message);
        }

        public NieBrakKwalifikatora(String message, Throwable cause) {
            super(message, cause);
        }

        public NieBrakKwalifikatora(Throwable cause) {
            super(cause);
        }

        public NieBrakKwalifikatora(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

}
