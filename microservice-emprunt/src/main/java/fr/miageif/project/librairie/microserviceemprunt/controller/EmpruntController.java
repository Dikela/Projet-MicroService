package fr.miageif.project.librairie.microserviceemprunt.controller;

import fr.miageif.project.librairie.microserviceemprunt.jpa.EmpruntJpa;
import fr.miageif.project.librairie.microserviceemprunt.modelBean.EmpruntLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EmpruntController {
    @Autowired
    private EmpruntJpa empruntJpa;

    //Liste tous les livres Empruntés
    @GetMapping(value = "/Emprunts")
    public List<EmpruntLivre> listeEmprunt() {
        return empruntJpa.findAll();
    }

    //Tous les livres emprunter
    @GetMapping(value = "/Emprunts/lecteur/{lecteur}")
    public List<EmpruntLivre> livreEmprunterParLecteur(@PathVariable String lecteur) {
        return empruntJpa.findByLecteur(lecteur);
    }

    // Afficher un livre prêté à une date donnée
    @GetMapping(value = "/Emprunts/DatePret/{datepret}")
    public List<EmpruntLivre> livresEmprunterAlaDate(@PathVariable String datepret) {
        return empruntJpa.findByDatepret(datepret);
    }

    //recherche des livre en cours d'emprunt
    @GetMapping(value = "/Emprunts/DateRetour/{dateretour}")
    public List<EmpruntLivre> livresRetournerAlaDate(@PathVariable String dateretour) {
        return empruntJpa.findByDateretour(dateretour);
    }

    //Afficher les livres en ecours de prêt
    @GetMapping(value = "/Emprunts/EnCours")
    public List<EmpruntLivre> livresEnCoursEmprunt() {
        List<EmpruntLivre> empruntEnCours = empruntJpa.findAll();
        List<EmpruntLivre> emprunt = new ArrayList<EmpruntLivre>();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = date.format(formatter);

        System.out.println(" La date corespondantes est    " + date1);

        for (EmpruntLivre emp : empruntEnCours) {
            if ((emp.getDateretour().compareTo(date1) >= 0)) {
                    //affiche les dates superieurs ou egales à la date d'aujourduit
                    emprunt.add(emp);
                    System.out.println(emp.getDateretour());
            }
        }
        return emprunt;
    }

    //retourner un livre ou supprimer un livre
    @DeleteMapping(value = "/Emprunts/{id}")
    public void retirerUnLivreEmprunter(@PathVariable int id){
        empruntJpa.delete(empruntJpa.findById(id));
    }

    // Rechercher un pret par Id
    @GetMapping(value = "/Emprunts/{id}")
    public EmpruntLivre cherhcerUnEmprunt(@PathVariable int id){
        return empruntJpa.findById(id);
    }

    @PostMapping("/Emprunts/")
    public ResponseEntity<EmpruntLivre> ajouterEmprunt(@RequestBody EmpruntLivre emprunt){
        EmpruntLivre emprunt1 = empruntJpa.save(emprunt);

        if (emprunt1 == null)
            return ResponseEntity.noContent().build();

        /*URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(emprunt.getId())
                .toUri();
        return ResponseEntity.created(location).build();*/
        return new ResponseEntity<EmpruntLivre>(emprunt, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Emprunts")
    public void updateEmprunt(@RequestBody EmpruntLivre emprunt){
        empruntJpa.save(emprunt);
    }



}