package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.TarefaPadrao;

public interface TarefaPadraoRepository extends JpaRepository<TarefaPadrao, Long> {
}