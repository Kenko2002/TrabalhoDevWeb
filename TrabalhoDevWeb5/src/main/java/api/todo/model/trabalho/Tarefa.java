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

@Entity
@Getter
@Setter
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String descricao;

	@Column
	private String nome;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "tipo_tarefa_id")
	private TipoTarefa tipo;


	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "historia_id")
	private HistoriaDeUsuario historia;


	@OneToMany(mappedBy = "tarefas", cascade = CascadeType.PERSIST)
	private List<HistoriaDeUsuario> historias = new ArrayList<>();


	@ManyToMany
	@JoinTable(name = "dependencia_tarefa", joinColumns = @JoinColumn(name = "terafa_id"), inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
	private List<Tarefa> dependentes;

	public Tarefa(){

	}

	public Tarefa(String nome, String descricao) {
		this.nome=nome;
		this.descricao=descricao;
	}

	public Tarefa(String nome, TipoTarefa tipo, String descricao) {
		this.nome=nome;
		this.tipo=tipo;
		this.descricao=descricao;
	}

	public List<HistoriaDeUsuario> getHistorias() {
		return historias;
	}
}


