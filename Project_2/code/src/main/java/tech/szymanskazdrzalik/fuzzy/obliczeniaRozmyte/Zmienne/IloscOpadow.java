package tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.Zmienne;

import tech.szymanskazdrzalik.fuzzy.model.Wypadek;
import tech.szymanskazdrzalik.fuzzy.obliczeniaRozmyte.PobierzWartosc;

public class IloscOpadow implements PobierzWartosc<Wypadek> {
    @Override
    public Double pobierzWartosc(Wypadek wypadek) {
        return wypadek.getIloscOpadow();
    }
}
