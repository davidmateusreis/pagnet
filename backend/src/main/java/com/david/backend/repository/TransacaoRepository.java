package com.david.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.david.backend.entity.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    List<Transacao> findAllByOrderByNomeDaLojaAscIdDesc();
}
