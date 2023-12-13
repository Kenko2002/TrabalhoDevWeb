package api.todo.model.trabalho;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Getter
@Setter
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tipo_id")
    private TipoTarefa tipo;
	
	@Column
    private String descricao;
	
	@Column
    private String nome;


	@OneToMany(mappedBy = "tarefas", cascade = CascadeType.ALL)
	private List<HistoriaDeUsuario> historias2 = new ArrayList<>();




	public Tarefa(){

	}
	
	public Tarefa(String nome, TipoTarefa tipo, String descricao) {
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


