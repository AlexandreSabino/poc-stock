package br.com.biscoito.repository;

import br.com.biscoito.entities.Kardex;
import br.com.biscoito.entities.KardexId;
import org.springframework.data.repository.CrudRepository;

public interface KardexRepository  extends CrudRepository<Kardex, KardexId>{
}
