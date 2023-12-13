package api.todo.model.trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.query.sqm.CastType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Getter
@Setter
public class Epico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome;
	
	@Column
    private String descricao;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipo_epico_id")
	private TipoEpico tipo; //C,R,U,D,L , CRUD+L
	

/* 
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "historia_tarefa",
			joinColumns = @JoinColumn(name = "historia_id"),
			inverseJoinColumns = @JoinColumn(name = "tarefa_id")
	)
	private List<Tarefa> tarefas = new ArrayList<>();
*/







/* 
	//epico.java	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "epico_historia",
		joinColumns = @JoinColumn(name = "epico_id"),
		inverseJoinColumns = @JoinColumn(name = "historia_id")
	)
	private List<HistoriaDeUsuario> historias = new ArrayList<>();
*/

	public void addHistoria(HistoriaDeUsuario historia) {
        historia.setEpico(this);
    }
}

