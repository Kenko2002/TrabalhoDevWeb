package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.Epico;
import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.Tarefa;
import api.todo.model.trabalho.TipoTarefa;
import api.todo.model.trabalho.TipoEpico;
import api.todo.repository.trabalho.EpicoRepository;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaRepository;
import api.todo.repository.trabalho.TipoEpicoRepository;

@Component
public class AppTipoEpico {

	@Autowired
	private EpicoRepository repository;

    @Autowired
	private TipoEpicoRepository repository_tipo_epico;

	@Autowired
	private HistoriaDeUsuarioRepository repository_hist;

	@Autowired
	private TarefaRepository repository_tarefa;
	
	//camada de logica de negocio
	
	private ArrayList<TipoEpico> epicos = new ArrayList<TipoEpico>();
	private Long lastId=Long.parseLong("0");
	
	public TipoEpico findById(Long id){
		return repository_tipo_epico.findById(id).orElse(null);
	}
	
	
	public ArrayList<TipoEpico> getAll(){
		return (ArrayList<TipoEpico>) repository_tipo_epico.findAll();
	}
	
	public TipoEpico create(TipoEpico epico) {
		/*for(int i=0;i<epico.getHistorias().size();i++) {
			repository_hist.save(epico.getHistorias().get(i));
		}*/
		/*if(epico.getTipo().equals("CRUD+L")) {
			//GerarHistorias2(epico);
		}*/
		return repository_tipo_epico.save(epico);
	}
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository_tipo_epico.deleteById(id);
	}


}