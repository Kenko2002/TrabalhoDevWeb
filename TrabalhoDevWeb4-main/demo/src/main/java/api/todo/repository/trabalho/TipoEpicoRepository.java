package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.TipoEpico;

public interface TipoEpicoRepository extends JpaRepository<TipoEpico, Long> {
}