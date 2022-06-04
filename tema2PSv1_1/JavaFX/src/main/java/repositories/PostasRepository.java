package repositories;

import model.Postas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostasRepository extends BaseRepository<Postas>{
    Postas save(Postas postas);

    //Postas saveOrUpdate(Postas postas);

    Postas findPostasByNumeUtilizatorAndParola(String numeUtilizator, String parola) ;

    List<Postas> findAll();

    List<Postas> findAllByNumeUtilizatorContaining(String nume);

    Postas findById(Integer id);

    void delete(Integer id);

    Postas removePostasByNumeUtilizatorAndParola(String numeUtilizator, String parola) ;
}
