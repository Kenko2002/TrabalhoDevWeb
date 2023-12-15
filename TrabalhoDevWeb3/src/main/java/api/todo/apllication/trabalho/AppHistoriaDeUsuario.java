package api.todo.apllication.trabalho;

import java.util.ArrayList;
import java.util.List;

import api.todo.arvore.ArvoreAVL;
import api.todo.arvore.ComparatorEpicoID;
import api.todo.arvore.ComparatorHistoriaID;
import api.todo.arvore.IArvoreBinaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.Epico;
import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.HistoriaDeUsuarioPadrao;
import api.todo.model.trabalho.Tarefa;
import api.todo.model.trabalho.TarefaPadrao;
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
		List<HistoriaDeUsuario> historias = repository.findAll();
		IArvoreBinaria<HistoriaDeUsuario> arvoreHistoria = new ArvoreAVL<>(new ComparatorHistoriaID());
		for (HistoriaDeUsuario historia : historias){
			arvoreHistoria.adicionar(historia);
		}
		return (ArrayList<HistoriaDeUsuario>) arvoreHistoria.caminharEmOrdemLista();
	}
	
	public HistoriaDeUsuario create(HistoriaDeUsuario historia) {
		for(int i=0;i<historia.getTarefas().size();i++) {
			repository_tarefas.save(historia.getTarefas().get(i));
		}
		GerarHistorias(historia);
		return repository.save(historia);
	}
	
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}
	
	



	public void GerarHistorias(HistoriaDeUsuario historia){
		for(int i=0; i<historia.getTipo().getTarefaspadrao().size() ;i++){

			TarefaPadrao tarefa_padrao = historia.getTipo().getTarefaspadrao().get(i);
			Tarefa tarefa_nova= new Tarefa(tarefa_padrao.getNome(),tarefa_padrao.getDescricao());
			tarefa_nova.setHistoria(historia);

			repository_tarefas.save(tarefa_nova);
		}

	}

}
