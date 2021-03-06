package fr.miageif.project.librairie.microservicelecteur.jpa;

import fr.miageif.project.librairie.microservicelecteur.modelBean.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LecteurJpa extends JpaRepository<Lecteur, String>, CrudRepository<Lecteur, String>  {
    Lecteur findById(int id);
}
