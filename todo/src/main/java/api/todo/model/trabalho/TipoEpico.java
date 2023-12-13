package api.todo.model.trabalho;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class TipoEpico{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome;

    @ManyToMany(cascade = CascadeType.PERSIST)
	private List<HistoriaDeUsuarioPadrao> historiaspadrao = new ArrayList<>();
    



    
    public TipoEpico(){

    }
    
    public TipoEpico(String nome){
        this.nome=nome;
    }

    

}


