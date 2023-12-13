package api.todo.repository.trabalho;

import org.springframework.data.jpa.repository.JpaRepository;

import api.todo.model.trabalho.HistoriaDeUsuarioPadrao;

public interface HistoriaDeUsuarioPadraoRepository extends JpaRepository<HistoriaDeUsuarioPadrao, Long> {
}