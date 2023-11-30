package com.david.backend.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Transacao(
        @Id Long id,
        Integer tipo,
        Date data,
        BigDecimal valor,
        Long cpf,
        String cartao,
        Time hora,
        @Column("dono_loja") String donoDaLoja,
        @Column("nome_loja") String nomeDaLoja) {

    public Transacao comValor(BigDecimal valor) {
        return new Transacao(id, tipo, data, valor, cpf, cartao, hora, donoDaLoja, nomeDaLoja);
    }

    public Transacao comData(String data) throws ParseException {
        var dataFormatada = new SimpleDateFormat("yyyyMMdd");
        var novaData = dataFormatada.parse(data);

        return new Transacao(
                id, tipo, new Date(novaData.getTime()), valor, cpf,
                cartao, hora, donoDaLoja, nomeDaLoja);
    }

    public Transacao comHora(String hora) throws ParseException {
        var horaFormatada = new SimpleDateFormat("HHmmss");
        var novaHora = horaFormatada.parse(hora);

        return new Transacao(
                id, tipo, data, valor, cpf,
                cartao, new Time(novaHora.getTime()), donoDaLoja, nomeDaLoja);
    }
}
