package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.TarefaPadrao;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaPadraoRepository;
import api.todo.repository.trabalho.TipoHistoriaDeUsuarioRepository;

@Component
public class AppTarefaPadrao {

	@Autowired
	private TarefaPadraoRepository repository;
	@Autowired
	private TipoHistoriaDeUsuarioRepository repository_hist;
	
	//camada de logica de negocio
	
	private ArrayList<TarefaPadrao> tarefas = new ArrayList<TarefaPadrao>();
	private Long lastId=Long.parseLong("0");
	
	public TarefaPadrao findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public TarefaPadrao updatebyid(Long id, TarefaPadrao tarefaNovo) {
		TarefaPadrao tarefa = repository.findById(id).orElse(null);
		tarefa.setNome(tarefaNovo.getNome());
		tarefa.setDescricao(tarefaNovo.getDescricao());
		repository.save(tarefa);
		return tarefa;
	}
	
	public ArrayList<TarefaPadrao> getAll(){
		return (ArrayList<TarefaPadrao>) repository.findAll();
	}
	
	public TarefaPadrao create(TarefaPadrao tarefa) {
        /*for(int i=0;i<tarefa.getHistorias().size();i++) {
			repository_hist.save(tarefa.getHistorias().get(i));
		}*/
		return repository.save(tarefa);
	}
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}
	
}