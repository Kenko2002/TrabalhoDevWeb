package api.todo.model.trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Getter
@Setter
public class HistoriaDeUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipo_historia_id")
	private TipoHistoriaDeUsuario tipo; //C,R,U,D,L , CRUD+L
	
	
	@Column
    private String descricao;
	
	
	// HistoriaDeUsuario historiaC = new HistoriaDeUsuario("C","Eu, como "+ator+", quero Criar "+acao);

	public HistoriaDeUsuario(){

	}

	public HistoriaDeUsuario(TipoHistoriaDeUsuario tipo, String descricao) {
		this.tipo=tipo;
		this.descricao=descricao;
	}


	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "historia_tarefa",
			joinColumns = @JoinColumn(name = "historia_id"),
			inverseJoinColumns = @JoinColumn(name = "tarefa_id")
	)
	private List<Tarefa> tarefas = new ArrayList<>();


	//historia de usuario.java
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "epico_id")
    private Epico epico;


	/*
	public List<Tarefa> addTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
        tarefa.getHistorias().add(this); // Atualize a outra extremidade da relação
        return this.tarefas;
    }
	*/

	public List<Tarefa> addTarefa(Tarefa tarefa) {
			this.tarefas.add(tarefa);
			tarefa.getHistorias2().add(this);
			return tarefas;
		}

	public List<Tarefa> addTarefa2(TarefaPadrao tarefa) {
				Tarefa tarefa_nova= new Tarefa();
				tarefa_nova.setDescricao(tarefa.getDescricao());
				tarefa_nova.setNome(tarefa.getNome());

				this.tarefas.add(tarefa_nova);
				return tarefas;
			}


}


