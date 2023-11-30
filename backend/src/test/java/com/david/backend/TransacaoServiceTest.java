package com.david.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.david.backend.entity.Transacao;
import com.david.backend.repository.TransacaoRepository;
import com.david.backend.service.TransacaoService;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Test
    public void testListTotaisTrasacoesByNomeDaLoja() {

        final String lojaA = "Loja A", lojaB = "Loja B";

        var transacao1 = new Transacao(1L, 1, new Date(System.currentTimeMillis()), BigDecimal.valueOf(100.0),
                111111111L,
                "1111-1111-1111-1111", new Time(System.currentTimeMillis()), "Dono da Loja A", lojaA);

        var transacao2 = new Transacao(2L, 1, new Date(System.currentTimeMillis()), BigDecimal.valueOf(50.0),
                222222222L,
                "2222-2222-2222-2222", new Time(System.currentTimeMillis()), "Dono da Loja B", lojaB);

        var transacao3 = new Transacao(3L, 1, new Date(System.currentTimeMillis()), BigDecimal.valueOf(75.0),
                333333333L,
                "3333-3333-3333-3333", new Time(System.currentTimeMillis()), "Dono da Loja A", lojaA);

        var mockTransacoes = List.of(transacao1, transacao2, transacao3);

        when(transacaoRepository.findAllByOrderByNomeDaLojaAscIdDesc())
                .thenReturn(mockTransacoes);

        var reports = transacaoService.listTotaisTransacoesPorNomeDaLoja();

        assertEquals(2, reports.size());

        reports.forEach(report -> {
            if (report.nomeDaLoja().equals(lojaA)) {
                assertEquals(2, report.transacoes().size());
                assertEquals(BigDecimal.valueOf(175.0), report.total());
                assertTrue(report.transacoes().contains(transacao1));
                assertTrue(report.transacoes().contains(transacao3));
            } else if (report.nomeDaLoja().equals(lojaB)) {
                assertEquals(1, report.transacoes().size());
                assertEquals(BigDecimal.valueOf(50.0), report.total());
                assertTrue(report.transacoes().contains(transacao2));
            }
        });
    }
}
