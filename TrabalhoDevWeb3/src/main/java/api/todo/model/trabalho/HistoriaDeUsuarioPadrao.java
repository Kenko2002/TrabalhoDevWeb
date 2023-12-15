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
public class HistoriaDeUsuarioPadrao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String descricao;
	

    @ManyToMany(cascade = CascadeType.PERSIST)
	private List<TarefaPadrao> tarefaspadrao2 = new ArrayList<>();
    
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipo_historia_id")
	private TipoHistoriaDeUsuario tipo;
	
	@OneToMany(mappedBy = "historiaspadrao", cascade = CascadeType.PERSIST)
	private List<TipoEpico> epico = new ArrayList<>();
	

    public HistoriaDeUsuarioPadrao(){

    }


}


