package br.com.biscoito.service;

import br.com.biscoito.entities.Kardex;
import br.com.biscoito.repository.KardexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KardexService {

    private final KardexRepository kardexRepository;

    public void save(final Kardex kardex) {
        kardexRepository.save(kardex);
    }
}
