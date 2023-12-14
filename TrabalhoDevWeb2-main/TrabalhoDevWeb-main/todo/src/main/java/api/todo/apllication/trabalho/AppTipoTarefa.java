package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.Tarefa;
import api.todo.model.trabalho.TipoTarefa;
import api.todo.repository.trabalho.TipoHistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TipoTarefaRepository;
import api.todo.repository.trabalho.TarefaPadraoRepository;

@Component
public class AppTipoTarefa {

	@Autowired
	private TipoTarefaRepository repository;

	@Autowired
	private TarefaPadraoRepository repository_tarefas;
	//camada de logica de negocio
	
	private ArrayList<TipoTarefa> tarefas = new ArrayList<TipoTarefa>();
	private Long lastId=Long.parseLong("0");
	
	public TipoTarefa findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	
	public ArrayList<TipoTarefa> getAll(){
		return (ArrayList<TipoTarefa>) repository.findAll();
	}
	





	public TipoTarefa create(TipoTarefa tarefa) {
			return repository.save(tarefa);
		}










	
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}

}
	
	