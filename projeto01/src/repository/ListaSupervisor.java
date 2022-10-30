package repository;

import domain.Supervisor;

import java.util.ArrayList;
import java.util.List;

public class ListaSupervisor {
    public static List<Supervisor> supervisores = new ArrayList<>();

    public static void adicionar(Supervisor sup) {
        supervisores.add(sup);
    }

    public static Supervisor retornar(Integer id) {
        return supervisores.get(id - 1);
    }

    public static List<Supervisor> retornarTodos() {
        return supervisores;
    }
}
