package fr.miageif.project.librairie.glivre.web.controller;
import fr.miageif.project.librairie.glivre.modelBean.Livre;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivreController {

    @RequestMapping(value="/Livre", method= RequestMethod.GET)
    public String listeLivre(){
        return "Voici la liste des livres dans notre BU";
    }

    /**
     * Cette methode permet de retourner un livre à travers sont isbn
     * @param id l'identifiant du livre
     * @return le livre correspondant
     */
    @RequestMapping(value="/Livre/{id}", method= RequestMethod.GET)
    public Livre afficherUnLivre(@PathVariable int id){
        Livre livre = new Livre(
                "250", "Michel",
                "Le coq chante", "Laye et Frere",
                2017
        );
        return livre;
    }
}
