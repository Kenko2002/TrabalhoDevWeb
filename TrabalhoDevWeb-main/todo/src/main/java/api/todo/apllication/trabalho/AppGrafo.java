package api.todo.apllication.trabalho;

import api.todo.grafo.Grafo;
import api.todo.grafo.Vertice;
import api.todo.model.trabalho.Epico;
import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.Tarefa;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class AppGrafo {

    @Autowired
	private HistoriaDeUsuarioRepository repository_hist;

    private Grafo<Object> grafo = new Grafo<>();

    public void criarGrafo(Epico epico) {
        adicionarEpico(epico);
        adicionarDependencias(epico);
    }

    private void adicionarEpico(Epico epico) {
        grafo.adicionaVertice(epico);
    }

    private void adicionarDependencias(Epico epico) {
        adicionarDependenciasHistorias(epico);
        adicionarDependenciasOutrosEpicos(epico);
    }

    private void adicionarDependenciasHistorias(Epico epico) {
        for (HistoriaDeUsuario historia : getHistoriadeusuarios(epico)) {
            adicionarDependenciasTarefas(historia);
            grafo.adicionarAresta(epico, historia, 1);
        }
    }

    private void adicionarDependenciasTarefas(HistoriaDeUsuario historia) {
        for (Tarefa tarefa : historia.getTarefas()) {
            grafo.adicionarAresta(historia, tarefa, 1);
        }
    }

    private void adicionarDependenciasOutrosEpicos(Epico epico) {
        for (Epico dependente : epico.getDependentes()) {
            grafo.adicionarAresta(epico, dependente, 1);
        }
    }

    public boolean temCiclo() {
        return grafo.temCiclo();
    }

    public Grafo<Object> getGrafo() {
        return grafo;
    }






    public List<HistoriaDeUsuario> getHistoriadeusuarios(Epico epico) {
        Long epicoId = epico.getId(); // Supondo que Epico tem um método getId()

        // Utilize o método findAllByEpicoId do seu repository
        return repository_hist.findAllByEpicoId(epicoId);
    }

}

