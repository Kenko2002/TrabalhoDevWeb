package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.Epico;

public interface EpicoRepository extends JpaRepository<Epico, Long> {
}