package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import api.todo.model.trabalho.Tarefa;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaRepository;

@Component
public class AppTarefa {

	@Autowired
	private TarefaRepository repository;
	@Autowired
	private HistoriaDeUsuarioRepository repository_hist;
	
	//camada de logica de negocio
	
	private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
	private Long lastId=Long.parseLong("0");
	
	public Tarefa findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public Tarefa updatebyid(Long id, Tarefa tarefaNovo) {
		Tarefa tarefa = repository.findById(id).orElse(null);
		tarefa.setNome(tarefaNovo.getNome());
		tarefa.setTipo(tarefaNovo.getTipo());
		tarefa.setDescricao(tarefaNovo.getDescricao());
		tarefa.setHistorias(tarefaNovo.getHistorias());
		repository.save(tarefa);
		return tarefa;
	}
	
	public ArrayList<Tarefa> getAll(){
		return (ArrayList<Tarefa>) repository.findAll();
	}
	
	public Tarefa create(Tarefa tarefa) {
		for(int i=0;i<tarefa.getHistorias().size();i++) {
			repository_hist.save(tarefa.getHistorias().get(i));
		}
		return repository.save(tarefa);
	}
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}
	
}