package api.todo.model.trabalho;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String tipo;
	
	@Column
    private String descricao;
	
	@Column
    private String nome;
	
	@ManyToMany(mappedBy = "tarefas")
    private List<HistoriaDeUsuario> historias = new ArrayList<>();
	
	public Tarefa(){

	}
	
	public Tarefa(String nome, String tipo, String descricao) {
		this.nome=nome;
		this.tipo=tipo;
		this.descricao=descricao;
	}
	
/*	public List<HistoriaDeUsuario> addHistoria(HistoriaDeUsuario historia){
		this.historias.add(historia);
		historia.getTarefas().add(this);
		return this.historias;
	}*/
}


