package api.todo.controller.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import api.todo.apllication.trabalho.AppDependencias;
import api.todo.apllication.trabalho.AppEpico;
import api.todo.apllication.trabalho.AppTipoEpico;
import api.todo.apllication.trabalho.AppHistoriaDeUsuario;
import api.todo.apllication.trabalho.AppTarefa;
import api.todo.apllication.trabalho.AppTipoHistoriaDeUsuario;
import api.todo.apllication.trabalho.AppTarefaPadrao;
//import api.todo.model.trabalho.Dependencias;
import api.todo.model.trabalho.Epico;
import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.Tarefa;
import api.todo.model.trabalho.TarefaPadrao;
import api.todo.model.trabalho.TipoHistoriaDeUsuario;
import api.todo.model.trabalho.TipoEpico;


//a funcao do controller é controlar as rota e as views da aplicação (url)

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/trabalho")
public class TrabalhoController {

	/*@Autowired
	private EpicoRepository epicoRepository;
	private HistoriaDeUsuarioRepository historiaRepository;
	private TarefaRepository tarefaRepository;*/
	
	@Autowired AppEpico appepico;
	@Autowired AppHistoriaDeUsuario apphistoria;
	@Autowired AppTarefa apptarefa;
	@Autowired AppTipoEpico apptipoepico;
	@Autowired AppTipoHistoriaDeUsuario apptipohistoria;
	@Autowired AppTarefaPadrao apptarefapadrao;
	
	//GET BY ID
	
	@GetMapping("getbyid/tarefa/{id}")
	public Tarefa getTarefaById(@PathVariable long id) {
		return apptarefa.findById(id);
	}
	@GetMapping("getbyid/historia/{id}")
	public HistoriaDeUsuario getHistoriaById(@PathVariable long id) {
		return apphistoria.findById(id);
	}
	@GetMapping("getbyid/epico/{id}")
	public Epico getEpicoById(@PathVariable long id) {
		return appepico.findById(id);
	}
	
	//GET ALL
	
	@GetMapping("/tarefa/getall")
	public ArrayList<Tarefa> TarefagetAll(){
		return apptarefa.getAll();
	}
	@GetMapping("/historia/getall")
	public ArrayList<HistoriaDeUsuario> HistoriagetAll(){
		return apphistoria.getAll();
	}
	@GetMapping("/epico/getall")
	public ArrayList<Epico> EpicogetAll(){
		return appepico.getAll();
	}

	@GetMapping("/tipohistoria/getall")
	public ArrayList<TipoHistoriaDeUsuario> TipohistoriagetAll(){
		return apptipohistoria.getAll();
	}
	@GetMapping("/tarefapadrao/getall")
	public ArrayList<TarefaPadrao> TarefaPadraogetAll(){
		return apptarefapadrao.getAll();
	}
	
	//CREATEs
	
	@PostMapping("/tarefa/")
	public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
		return apptarefa.create(tarefa);
	}
	@PostMapping("/historia/")
	public HistoriaDeUsuario createHistoria(@RequestBody HistoriaDeUsuario historia) {
		return apphistoria.create(historia);
	}
	@PostMapping("/epico/")
	public Epico createEpico(@RequestBody Epico epico) {
		return appepico.create(epico);
	}

	@PostMapping("/tipoepico/")
	public TipoEpico createTipoEpico(@RequestBody TipoEpico tipoepico) {
		return apptipoepico.create(tipoepico);
	}
	@PostMapping("/tipohistoria/")
	public TipoHistoriaDeUsuario createTipoHistoria(@RequestBody TipoHistoriaDeUsuario tipohistoria) {
		return apptipohistoria.create(tipohistoria);
	}
	@PostMapping("/tarefapadrao/")
	public TarefaPadrao createTarefaPadrao(@RequestBody TarefaPadrao tarefapadrao) {
		return apptarefapadrao.create(tarefapadrao);
	}


	
	//UPDATE BY IDs
	
	
	@PutMapping("/tarefa/updatebyid/{id}")
	public Tarefa updatebyidTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
		return apptarefa.updatebyid(id,tarefa);
	}
	@PutMapping("/historia/updatebyid/{id}")
	public HistoriaDeUsuario updatebyidHistoria(@PathVariable Long id, @RequestBody HistoriaDeUsuario historia) {
		return apphistoria.updatebyid(id,historia);
	}
	@PutMapping("/epico/updatebyid/{id}")
	public Epico updatebyidEpico(@PathVariable Long id, @RequestBody Epico epico) {
		return appepico.updatebyid(id,epico);
	}
	
	//DELETEs
	
	@DeleteMapping("/tarefa/{id}/delete_produto")
	public void deletetarefa(@PathVariable Long id) {
		apptarefa.delete(id);
	}
	@DeleteMapping("/historia/{id}/delete_produto")
	public void deletehistoria(@PathVariable Long id) {
		apphistoria.delete(id);
	}
	@DeleteMapping("/epico/{id}/delete_produto")
	public void deleteepico(@PathVariable Long id) {
		appepico.delete(id);
	}
	
	
	



	




	/*
	
	//DEPENDENCIAS
	@PostMapping("/tarefa/createDependencias")
	public Dependencias<Tarefa> createDependenciasTarefa(@RequestBody Dependencias<Tarefa> arvore_dependencias_tarefas) {
		return appdependencias.create(arvore_dependencias_tarefas);
	}
	
	@GetMapping("getbyid/tarefa/dependencias/{id}")
	public Dependencias<Tarefa> findById(@PathVariable long id) {
		return appdependencias.findById(id);
	}
	
	*/

}


