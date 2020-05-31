package fr.miageif.project.librairie.client.proxies;

import fr.miageif.project.librairie.client.beans.EmpruntBean;
import fr.miageif.project.librairie.client.beans.LecteurBean;
import fr.miageif.project.librairie.client.beans.LivreBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-emprunt", url = "localhost:8000")
public interface EmpruntProxy {

    @GetMapping(value = "/Emprunts")
    List<EmpruntBean> listeEmprunt();

    @GetMapping(value = "/Emprunts/EnCours")
    List<EmpruntBean> livresEnCoursEmprunt();

    @GetMapping(value = "/Livres/{isbn}")
    LivreBean afficherUnLivre(@PathVariable("isbn") String isbn);

    @GetMapping(value = "/Lecteurs/{id}")
    LecteurBean afficherUnLecteur(@PathVariable("id") int id);

    @PostMapping("/Emprunts/")
    ResponseEntity<EmpruntBean> ajouterEmprunt(@RequestBody EmpruntBean emprunt);

    @GetMapping(value = "/Emprunts/{id}")
    EmpruntBean cherhcerUnEmprunt(@PathVariable("id") int id);

    @DeleteMapping(value = "/Emprunts/{id}")
    void retirerUnLivreEmprunter(@PathVariable("id") int id);
}
