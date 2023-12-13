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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Getter
@Setter
public class TipoHistoriaDeUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome_tipo;
	
	
    
	/* 
	@JoinTable(
			name = "tipohistoria_padraotarefa",
			joinColumns = @JoinColumn(name = "tipohistoria_id"),
			inverseJoinColumns = @JoinColumn(name = "padraotarefa_id")
	)
	*/
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<TarefaPadrao> tarefaspadrao = new ArrayList<>();
    


}


