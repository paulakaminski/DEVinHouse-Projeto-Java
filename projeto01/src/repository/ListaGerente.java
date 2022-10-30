package repository;

import domain.Gerente;

import java.util.ArrayList;
import java.util.List;

public class ListaGerente {
    public static List<Gerente> gerentes = new ArrayList<>();

    public static void adicionar(Gerente ger) {
        gerentes.add(ger);
    }

    public static Gerente retornar(Integer id) {
        return gerentes.get(id - 1);
    }

    public static List<Gerente> retornarTodos() {
        return gerentes;
    }
}
