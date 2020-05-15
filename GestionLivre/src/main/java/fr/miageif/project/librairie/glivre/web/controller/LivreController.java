package fr.miageif.project.librairie.glivre.web.controller;
import fr.miageif.project.librairie.glivre.jpa.LivreJpa;
import fr.miageif.project.librairie.glivre.modelBean.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class LivreController {
    @Autowired
    private LivreJpa livreJpa;

    /**
     * Affiche tous les livres contenus dans la base de données
     * */
    @GetMapping(value = "Livres")
    public List<Livre> listeLivres(){
        return livreJpa.findAll();
    }
    /**
     *
     *
     * @return*/
    @GetMapping(value = "/Livres/{isbn}")
    public Livre afficherUnLivre(@PathVariable String isbn){
        return livreJpa.findByIsbn(isbn);
    }

    /*
    **/
    @PostMapping(value = "/Livres")
    public ResponseEntity<Void> ajouterLivre(@RequestBody Livre livre){

        Livre livre1 = livreJpa.save(livre);

        if (livre1 == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{isbn}")
                .buildAndExpand(livre1.getIsbn())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/Livres/{isbn}")
    public void retirerLivre(@PathVariable String isbn){
        livreJpa.delete(livreJpa.findByIsbn(isbn));
    }

    @PutMapping(value = "/Livres")
    public void updateLivre(@RequestBody Livre livre){
        livreJpa.save(livre);
    }
}
