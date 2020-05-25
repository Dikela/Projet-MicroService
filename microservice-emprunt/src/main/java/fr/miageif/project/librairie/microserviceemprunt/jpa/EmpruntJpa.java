package fr.miageif.project.librairie.microserviceemprunt.jpa;
import fr.miageif.project.librairie.microserviceemprunt.modelBean.EmpruntLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmpruntJpa extends JpaRepository<EmpruntLivre, Integer>, CrudRepository<EmpruntLivre, Integer>{
    /* List<EmpruntLivre> findByDate_pret(String datepret); */
    List<EmpruntLivre> findByLecteur(String lecteur);
    List<EmpruntLivre> findByDatepret(String datepret);
    List<EmpruntLivre> findByDateretour(String dateretour);
    EmpruntLivre findById(int id);
}