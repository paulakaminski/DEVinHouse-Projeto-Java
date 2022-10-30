package repository;

import domain.Colaborador;

import java.util.ArrayList;
import java.util.List;

public class ListaColaborador {
    public static List<Colaborador> colaboradores = new ArrayList<>();

    public static void adicionar(Colaborador colaborador) {
        colaboradores.add(colaborador);
    }

    public static Colaborador retornar(Integer id) {
        return colaboradores.get(id - 1);
    }

    public static Colaborador retornarUltimo() {
        return colaboradores.get(colaboradores.size() - 1);
    }

    public static List<Colaborador> retornarTodos() {
        return colaboradores;
    }
}
