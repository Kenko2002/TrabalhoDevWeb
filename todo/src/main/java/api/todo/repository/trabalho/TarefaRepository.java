package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}