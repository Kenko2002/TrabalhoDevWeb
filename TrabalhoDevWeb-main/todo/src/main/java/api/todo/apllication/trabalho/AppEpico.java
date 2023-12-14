package api.todo.apllication.trabalho;

import java.util.ArrayList;

import api.todo.grafo.Grafo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.Epico;
import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.HistoriaDeUsuarioPadrao;
import api.todo.model.trabalho.Tarefa;
import api.todo.model.trabalho.TarefaPadrao;
import api.todo.repository.trabalho.EpicoRepository;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaRepository;

@Component
public class AppEpico {

	private final Grafo<Epico> epicoGrafo = new Grafo<Epico>();
	@Autowired
	private EpicoRepository repository;

	@Autowired
	private HistoriaDeUsuarioRepository repository_hist;

	@Autowired
	private TarefaRepository repository_tarefa;

	//camada de logica de negocio

	private ArrayList<Epico> epicos = new ArrayList<Epico>();
	private Long lastId=Long.parseLong("0");
	public Epico findById(Long id){
		return repository.findById(id).orElse(null);
	}

	public Epico updatebyid(Long id, Epico epicoNovo) {
		Epico epico = repository.findById(id).orElse(null);
		epico.setDescricao(epicoNovo.getDescricao());
		epico.setNome(epicoNovo.getNome());
		//epico.setHistorias(epicoNovo.getHistorias());
		repository.save(epico);
		return epico;
	}

	public ArrayList<Epico> getAll(){
		return (ArrayList<Epico>) repository.findAll();
	}

	public Epico create(Epico epico) {
		GerarHistorias(epico);
		return repository.save(epico);
	}

	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}

	public void GerarHistorias(Epico epico){
		for(int i=0; i<epico.getTipo().getHistoriaspadrao().size() ;i++){

			HistoriaDeUsuarioPadrao historia_padrao = epico.getTipo().getHistoriaspadrao().get(i);
			HistoriaDeUsuario historia_nova= new HistoriaDeUsuario(historia_padrao.getTipo(),historia_padrao.getDescricao());
			historia_nova.setEpico(epico);
			historia_nova.setTipo(historia_padrao.getTipo());

			for(int j=0;j<historia_padrao.getTarefaspadrao2().size();j++){


				historia_nova.addTarefa2(historia_padrao.getTarefaspadrao2().get(j));


				TarefaPadrao tarefa_padrao = historia_padrao.getTarefaspadrao2().get(j);
				Tarefa tarefa_nova= new Tarefa();
				tarefa_nova.setDescricao(tarefa_padrao.getDescricao());
				tarefa_nova.setNome(tarefa_padrao.getNome());

				//repository_tarefa.save(tarefa_nova);

			}
			repository_hist.save(historia_nova);
		}

	}
}