package api.todo.arvore;

import java.util.Comparator;

import api.todo.model.trabalho.Epico;

public class ComparatorEpicoID implements Comparator<Epico>{

    @Override
    public int compare(Epico o1, Epico o2) {
        return Long.compare(o1.getId(), o2.getId());
    }

    
}
