package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.TipoHistoriaDeUsuario;

public interface TipoHistoriaDeUsuarioRepository extends JpaRepository<TipoHistoriaDeUsuario, Long> {
}