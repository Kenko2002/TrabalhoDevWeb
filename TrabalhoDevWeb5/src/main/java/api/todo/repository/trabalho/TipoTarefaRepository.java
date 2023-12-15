package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.TipoTarefa;

public interface TipoTarefaRepository extends JpaRepository<TipoTarefa, Long> {
}