package api.todo.apllication.trabalho;

import api.todo.grafo.Grafo;
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
        verificarCiclo();
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
            // Lógica para lidar com ciclos, como lançar uma exceção
            throw new RuntimeException("Ciclo detectado no grafo!");
        }
        else {
            System.out.println("NÃO TEM CICLO HURUL\n\n\n\n\n\n\nNÃO TEM CICLO");
        }
    }

    public List<HistoriaDeUsuario> getHistoriadeusuarios(Epico epico) {
        Long epicoId = epico.getId(); // Supondo que Epico tem um método getId()

        // Utilize o método findAllByEpicoId do seu repository
        return repository_hist.findAllByEpicoId(epicoId);
    }
}
