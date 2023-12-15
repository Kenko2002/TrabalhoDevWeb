package api.todo.model.trabalho;

import java.util.List;
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

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "dependencia_epico",
			joinColumns = @JoinColumn(name = "epico_id"),
			inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
	private List<Epico> dependentes;


}

