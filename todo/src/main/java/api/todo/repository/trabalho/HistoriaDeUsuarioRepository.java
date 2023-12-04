package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.HistoriaDeUsuario;

public interface HistoriaDeUsuarioRepository extends JpaRepository<HistoriaDeUsuario, Long> {
}
