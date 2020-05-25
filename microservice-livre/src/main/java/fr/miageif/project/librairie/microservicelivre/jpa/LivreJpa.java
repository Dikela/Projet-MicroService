package fr.miageif.project.librairie.microservicelivre.jpa;

import fr.miageif.project.librairie.microservicelivre.modelBean.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreJpa extends JpaRepository<Livre, String>, CrudRepository<Livre, String> {
    Livre findByIsbn(String isbn);// redefinir la methode findById de la doc de JPA
    //void deleteByIsbn(String isbn);
}