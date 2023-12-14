package api.todo.arvore;

import java.util.Comparator;

import api.todo.model.trabalho.HistoriaDeUsuario;

public class ComparatorHistoriaID extends Comparator<HistoriaDeUsuario>{

    @Override
    public int compare(HistoriaDeUsuario o1, HistoriaDeUsuario o2) {
        return Long.compare(o1.getId(), o2.getId());
    }

    
}
