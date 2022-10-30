package repository;

import domain.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ListaFuncionario {
    public static List<Funcionario> funcionarios = new ArrayList<>();

    public static void adicionar(Funcionario func) {
        funcionarios.add(func);
    }

    public static Funcionario retornar(Integer id) {
        return funcionarios.get(id - 1);
    }

    public static List<Funcionario> retornarTodos() {
        return funcionarios;
    }
}
