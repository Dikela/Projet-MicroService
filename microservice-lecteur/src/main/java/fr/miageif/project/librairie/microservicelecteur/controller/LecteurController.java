package fr.miageif.project.librairie.microservicelecteur.controller;

import fr.miageif.project.librairie.microservicelecteur.jpa.LecteurJpa;
import fr.miageif.project.librairie.microservicelecteur.modelBean.Lecteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Contrôleur Rest qui permet d'indiquer les requêtes  que nous définissons. Ainsi, chaque réponse va renvoyer une réponse JSON à àl'utilisateur.
@RestController
public class LecteurController {
    @Autowired
    private LecteurJpa lecteurJpa;

    // Affiche tous les lecteurs contenus dans la base de données
    @GetMapping(value = "Lecteurs")
    public List<Lecteur> listeLecteurs(){
        return lecteurJpa.findAll();
    }

    // recupère un lecteur par son ID
    @GetMapping(value = "/Lecteurs/{id}")
    public Lecteur afficherUnLecteur(@PathVariable int id){
        System.out.println(lecteurJpa.findById(id));
        return lecteurJpa.findById(id);
    }
    // Permet de créer un lecteur
    @PostMapping(value = "/Lecteurs")
    public ResponseEntity<Void> ajouterLecteur(@RequestBody Lecteur lecteur) {
        Lecteur lecteur1 = lecteurJpa.save(lecteur);
        if (lecteur1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lecteur.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    //Supprime une lecteur par son ID
    @DeleteMapping(value = "/Lecteurs/{id}")
    public void retirerLecteur(@PathVariable int id){
        lecteurJpa.delete(lecteurJpa.findById(id));
    }

    //Mets à jour les informations d'un lecteur
    @PutMapping(value = "/Lecteurs")
    public void updateLecteur(@RequestBody Lecteur lecteur){
        lecteurJpa.save(lecteur);
    }
}
