package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaRepository;

@Component
public class AppHistoriaDeUsuario {

	@Autowired
	private HistoriaDeUsuarioRepository repository;

	@Autowired
	private TarefaRepository repository_tarefas;
	//camada de logica de negocio
	
	private ArrayList<HistoriaDeUsuario> tarefas = new ArrayList<HistoriaDeUsuario>();
	private Long lastId=Long.parseLong("0");
	
	public HistoriaDeUsuario findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public HistoriaDeUsuario updatebyid(Long id, HistoriaDeUsuario historiaNovo) {
		HistoriaDeUsuario historiaDeUsuario = repository.findById(id).orElse(null);
		historiaDeUsuario.setTipo(historiaNovo.getTipo());
		historiaDeUsuario.setDescricao(historiaNovo.getDescricao());
		historiaDeUsuario.setTarefas(historiaNovo.getTarefas());
		repository.save(historiaDeUsuario);
		return historiaDeUsuario;
	}
	
	public ArrayList<HistoriaDeUsuario> getAll(){
		return (ArrayList<HistoriaDeUsuario>) repository.findAll();
	}
	
	public HistoriaDeUsuario create(HistoriaDeUsuario historia) {
		for(int i=0;i<historia.getTarefas().size();i++) {
			repository_tarefas.save(historia.getTarefas().get(i));
		}
		return repository.save(historia);
	}
	
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}
	
	
	
}