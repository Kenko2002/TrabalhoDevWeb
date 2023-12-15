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

import java.util.ArrayList;
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
        verificarCiclo();
        ordenar();
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
        if( epico.getDependentes()!=null){
            for (Epico dependente : epico.getDependentes()) {
                if (!grafo.temAresta(epico, dependente)) {
                    grafo.adicionarAresta(epico, dependente, 1);
                }
            }
        }
    }
    private void verificarCiclo() {
        if (grafo.temCiclo()) {
            System.out.println("TEM CICLO HURUL\n\n\n\n\n\n\nTEM CICLO");
            throw new RuntimeException("Ciclo detectado no grafo!");
        }
        else {
            System.out.println("NÃO TEM CICLO HURUL\n\n\n\n\n\n\nNÃO TEM CICLO");
        }
    }

    public void ordenar() {
        List<Vertice<Object>> resultado = grafo.ordenacaoTopologica();

        List<Object> objetosOrdenados = new ArrayList<>();

        // Iterar sobre os vértices ordenados
        for (Vertice<Object> vertice : resultado) {
            // Adicionar o valor do vértice à lista de objetos
            objetosOrdenados.add(vertice.getValor());
        }

        for (Object objeto : objetosOrdenados) {
            if (objeto instanceof Epico) {
                Epico epico = (Epico) objeto;
                System.out.println("Epico: " + epico.getDescricao());
            } else if (objeto instanceof HistoriaDeUsuario) {
                HistoriaDeUsuario historia = (HistoriaDeUsuario) objeto;
                System.out.println("História: " + historia.getDescricao());
            } else if (objeto instanceof Tarefa) {
                Tarefa tarefa = (Tarefa) objeto;
                System.out.println("Tarefa: " + tarefa.getDescricao());
            }
        }
    }

    public List<HistoriaDeUsuario> getHistoriadeusuarios(Epico epico) {
        Long epicoId = epico.getId(); // Supondo que Epico tem um método getId()

        // Utilize o método findAllByEpicoId do seu repository
        return repository_hist.findAllByEpicoId(epicoId);
    }
}
