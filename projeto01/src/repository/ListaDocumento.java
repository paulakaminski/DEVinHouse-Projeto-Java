package repository;

import domain.documento.Documento;

import java.util.ArrayList;
import java.util.List;

public class ListaDocumento {
    public static List<Documento> documentos = new ArrayList<>();

    public static void adicionar(Documento documento) {
        documentos.add(documento);
    }

    public static List<Documento> retornarTodos() {
        return documentos;
    }

    public static Documento retornar(Integer idDocumento) {
        return documentos.get(idDocumento - 1);
    }
}
