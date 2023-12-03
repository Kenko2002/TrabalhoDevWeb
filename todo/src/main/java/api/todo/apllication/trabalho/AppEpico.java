package api.todo.apllication.trabalho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.todo.model.trabalho.Epico;
import api.todo.model.trabalho.HistoriaDeUsuario;
import api.todo.model.trabalho.Tarefa;
import api.todo.repository.trabalho.EpicoRepository;
import api.todo.repository.trabalho.HistoriaDeUsuarioRepository;
import api.todo.repository.trabalho.TarefaRepository;

@Component
public class AppEpico {

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
		epico.setHistorias(epicoNovo.getHistorias());
		repository.save(epico);
		return epico;
	}
	
	public ArrayList<Epico> getAll(){
		return (ArrayList<Epico>) repository.findAll();
	}
	
	public Epico create(Epico epico) {
		for(int i=0;i<epico.getHistorias().size();i++) {
			repository_hist.save(epico.getHistorias().get(i));
		}
		if(epico.getTipo().equals("CRUD+L")) {
			//GerarHistorias(epico);
			GerarHistorias2(epico);
		}
		return repository.save(epico);
	}
	
	public void delete(Long id) {
		//this.produtos.removeIf(produto -> produto.getId().equals(id));
		repository.deleteById(id);
	}




	public void GerarHistorias2(Epico epico){
		String minhaString = epico.getDescricao();

        String[] partes = minhaString.split(",");

        if (partes.length >= 3) {
            String parte1 = partes[1].split(" ")[2].trim(); // "Professor" ->Ator
            
            String segunda_sentença = partes[2].trim(); // "quero Criar um aluno em uma turma"
            
            String[] parte2 = segunda_sentença.split(" ");
            String resultado = "";

            if (parte2.length >= 2) {
                resultado = segunda_sentença.substring(parte2[0].length() + parte2[1].length() + 2);
            } //resultado="um aluno em uma turma" -> Tarefa
            
            //System.out.println("Parte 2: /" + parte2 + "/");
            //System.out.println("Parte 3: /" + parte3 + "/");
            
            String ator=parte1;
            String acao=resultado;

			for (int i = 0; i < epico.getTipo().length(); i++) {
				if(epico.getTipo().charAt(i)=='C'){
					HistoriaDeUsuario historiaC = new HistoriaDeUsuario("C","Eu, como "+ator+", quero Criar "+acao);
					repository_hist.save(historiaC);
					epico.addHistoria(historiaC);
					repository.save(epico);
					GerarTarefas(historiaC,epico);
				}
				if(epico.getTipo().charAt(i)=='R'){
					HistoriaDeUsuario historiaR = new HistoriaDeUsuario("R","Eu, como "+ator+", quero Buscar "+acao);
					repository_hist.save(historiaR);
					epico.addHistoria(historiaR);
					repository.save(epico);
					GerarTarefas(historiaR,epico);
				}
				if(epico.getTipo().charAt(i)=='U'){
					HistoriaDeUsuario historiaU = new HistoriaDeUsuario("U","Eu, como "+ator+", quero Atualizar "+acao);
					repository_hist.save(historiaU);
					epico.addHistoria(historiaU);
					repository.save(epico);
					GerarTarefas(historiaU,epico);
				}
				if(epico.getTipo().charAt(i)=='D'){
					HistoriaDeUsuario historiaD = new HistoriaDeUsuario("D","Eu, como "+ator+", quero Deletar "+acao);
					repository_hist.save(historiaD);
					epico.addHistoria(historiaD);
					repository.save(epico);
					GerarTarefas(historiaD,epico);
				}
				if(epico.getTipo().charAt(i)=='L'){
					HistoriaDeUsuario historiaL = new HistoriaDeUsuario("L","Eu, como "+ator+", quero Listar "+acao);
            		repository_hist.save(historiaL);
					epico.addHistoria(historiaL);
					repository.save(epico);
					GerarTarefas(historiaL,epico);
					HistoriaDeUsuario historiaLALL = new HistoriaDeUsuario("LALL","Eu, como "+ator+", quero Listar todos "+acao);
           			repository_hist.save(historiaLALL);
					epico.addHistoria(historiaLALL);
					repository.save(epico);
					GerarTarefas(historiaLALL,epico);
				}
				

			}
	}

	
        
		
	}

	public void GerarTarefas(HistoriaDeUsuario historia,Epico epico){
		if(epico.getBack_ou_front_end().equals("Back-End")) {
            	Tarefa tarefa1= new Tarefa("Criar Rota","Back-End","Criar Rota");
            	Tarefa tarefa2= new Tarefa("Criar Controlador","Back-End","Criar Controlador");
            	Tarefa tarefa3= new Tarefa("Criar View","Back-End","Criar View");
            	Tarefa tarefa4= new Tarefa("Criar Testes","Testes","Criar Testes");
            	Tarefa tarefa5= new Tarefa("Criar Documentação","Documentação","Criar Documentação");
				historia.addTarefa(tarefa1);
				historia.addTarefa(tarefa2);
				historia.addTarefa(tarefa3);
				historia.addTarefa(tarefa4);
				historia.addTarefa(tarefa5);		
				//repository_hist.save(historia);
				repository_tarefa.save(tarefa1); 
				repository_tarefa.save(tarefa2);
				repository_tarefa.save(tarefa3);
				repository_tarefa.save(tarefa4);
				repository_tarefa.save(tarefa5);

		}
	}

}
