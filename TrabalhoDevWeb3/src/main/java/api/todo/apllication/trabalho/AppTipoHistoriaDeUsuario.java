package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.Tarefa;
import api.todo.model.trabalho.TipoHistoriaDeUsuario;
import api.todo.repository.trabalho.TipoHistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaPadraoRepository;

@Component
public class AppTipoHistoriaDeUsuario {

	@Autowired
	private TipoHistoriaDeUsuarioRepository repository;

	@Autowired
	private TarefaPadraoRepository repository_tarefas;
	//camada de logica de negocio
	
	private ArrayList<TipoHistoriaDeUsuario> tarefas = new ArrayList<TipoHistoriaDeUsuario>();
	private Long lastId=Long.parseLong("0");
	
	public TipoHistoriaDeUsuario findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public TipoHistoriaDeUsuario updatebyid(Long id, TipoHistoriaDeUsuario historiaNovo) {
		TipoHistoriaDeUsuario historiaDeUsuario = repository.findById(id).orElse(null);
		historiaDeUsuario.setNome_tipo(historiaNovo.getNome_tipo());
		historiaDeUsuario.setTarefaspadrao(historiaNovo.getTarefaspadrao());
		repository.save(historiaDeUsuario);
		return historiaDeUsuario;
	}
	
	public ArrayList<TipoHistoriaDeUsuario> getAll(){
		return (ArrayList<TipoHistoriaDeUsuario>) repository.findAll();
	}
	





	public TipoHistoriaDeUsuario create(TipoHistoriaDeUsuario historia) {
			for(int i=0;i<historia.getTarefaspadrao().size();i++) {
				repository_tarefas.save(historia.getTarefaspadrao().get(i));
			}
			return repository.save(historia);
		}










	
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}
	
	
	
}