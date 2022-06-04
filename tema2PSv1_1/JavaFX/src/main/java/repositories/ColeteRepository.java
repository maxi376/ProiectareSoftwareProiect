package repositories;

import model.Colet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColeteRepository  extends BaseRepository<Colet>{
    Colet save(Colet colet);

    //Colet saveOrUpdate(Colet colet);

    Colet findColetByDestAndGreutate (String dest, Integer greutate);

    List<Colet> findAll();

    Colet findById(Integer id);

    List<Colet> findAllByNumePostasContaining(String numePostas);

    void    delete (Integer id);

    Colet removeColetByDestAndGreutate (String dest, Integer greutate);
}
