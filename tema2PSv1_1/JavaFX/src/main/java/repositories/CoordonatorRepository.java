package repositories;

import model.CoordonatorActivitate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordonatorRepository extends BaseRepository<CoordonatorActivitate> {
    CoordonatorActivitate save(CoordonatorActivitate coordonatorActivitate);

    //CoordonatorActivitate saveOrUpdate(CoordonatorActivitate coordonatorActivitate);

    CoordonatorActivitate findCoordonatorActivitateByNumeUtilizatorAndParola(String numeUtilizator, String parola) ;

    List<CoordonatorActivitate> findAll ();

    CoordonatorActivitate findById(Integer id);

    void delete(Integer id);

    CoordonatorActivitate removeCoordonatorActivitateByNumeUtilizatorAndParola(String numeUtilizator, String parola) ;
}
