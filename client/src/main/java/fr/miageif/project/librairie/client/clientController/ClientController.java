package fr.miageif.project.librairie.client.clientController;

import fr.miageif.project.librairie.client.beans.EmpruntBean;
import fr.miageif.project.librairie.client.beans.LecteurBean;
import fr.miageif.project.librairie.client.beans.LivreBean;
import fr.miageif.project.librairie.client.proxies.ClientProxy;
import fr.miageif.project.librairie.client.proxies.EmpruntProxy;
import fr.miageif.project.librairie.client.proxies.LecteurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ClientController {

    @Autowired()
    private ClientProxy clientProxy;

    @Autowired()
    private LecteurProxy lecteurProxy;

    @Autowired()
    private EmpruntProxy empruntProxy;

    @RequestMapping("/")
    public String acceuil(Model model){
        return "Index";
    }

    @RequestMapping("/Livres")
    public String livreAcceuil(Model model){
        List<LivreBean> livres =  clientProxy.listeLivres();
        model.addAttribute("livres", livres);
        return "AccueilLivre";
    }

    @RequestMapping("/details-livre/{isbn}")
    public String ficheLivre(@PathVariable String isbn, Model model){
        LivreBean livre = clientProxy.afficherUnLivre(isbn);
        model.addAttribute("livre", livre);
        return "FicheLivre";
    }

    @RequestMapping("/Lecteurs")
    public String lecteurAcceuil(Model model){
        List<LecteurBean> lecteurs =  lecteurProxy.listeLecteurs();
        model.addAttribute("lecteurs", lecteurs);
        return "AccueilLecteur";
    }

    @RequestMapping("/details-lecteur/{id}")
    public String ficheLecteur(@PathVariable int id, Model model) {
        LecteurBean lecteur = lecteurProxy.afficherUnLecteur(id);
        model.addAttribute("lecteur", lecteur);
        return "lecteurFiche";
    }

    @RequestMapping("/details-livre/emprunter-livre/{isbn}")
    public String emprunterLivre(@PathVariable String isbn, Model model){

        LivreBean livre = clientProxy.afficherUnLivre(isbn);
        List<LecteurBean> lecteurs =  lecteurProxy.listeLecteurs();

        model.addAttribute("lecteurs", lecteurs);
        model.addAttribute("livre", livre);
        return "Emprunter";
    }

    @RequestMapping("/Emprunts")
    public String EmpruntAcceuil(Model model){
        List<EmpruntBean> emprunts =  empruntProxy.listeEmprunt();
        model.addAttribute("emprunts", emprunts);
        return "AcceuilEmprunt";
    }

    /**
     * Controller qui permet de valider un emprunt et creer un nouvel
     * @param id, correspond à l'id du lecteur
     * @param isbn l'identifiant du livre
     * @param model model pour gérer les données dans le script html
     * @return la donnée à exploiter en html
     */
    @RequestMapping("/details-lecteurEmprunt/{id}/{isbn}")
    public String ValidePretLivre(@PathVariable int id, @PathVariable String isbn, Model model){
        List<EmpruntBean> emprunts =  empruntProxy.listeEmprunt();
        int size = emprunts.size();
        List<Integer> idliste = new ArrayList<Integer>();
        for (int i = 0; i <size ; i++) {
            idliste.add(emprunts.get(i).getId());
        }
        int maxValue = Collections.max(idliste);

        System.out.println(" la taille est avec size: " + size);
        System.out.println(" la taille est avec max: " + maxValue);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = date.format(formatter);

        //Chercher le livre correspondant
        LivreBean livre = clientProxy.afficherUnLivre(isbn);
        //Chercher le lecteur correspondant
        LecteurBean lecteur = lecteurProxy.afficherUnLecteur(id);
        //Creer un bouveau emprunt
        String lecteurId = Integer.toString(lecteur.getId());

        EmpruntBean empruntBean = new EmpruntBean();
        String isbn1 = livre.getIsbn();

        empruntBean.setId(size + 1);
        empruntBean.setISBN(isbn1);
        empruntBean.setLecteur(lecteurId);
        empruntBean.setDatepret(date1);
        empruntBean.setDateretour(date1);
        ResponseEntity<EmpruntBean> empruntBean1 = empruntProxy.ajouterEmprunt(empruntBean);

        if (empruntBean1.getStatusCode() == HttpStatus.CREATED)
            System.out.println(" Emprunt effectué avec succès  ");
        return "ValiderPret";
    }

    @RequestMapping(value = "/Emprunts/EnCours")
    public String EmpruntEncours(Model model){
        List<EmpruntBean> emprunts =  empruntProxy.livresEnCoursEmprunt();
        model.addAttribute("emprunts", emprunts);
        return "LivresEncoursEmprunt";
    }

    @RequestMapping("/details-emprunt/{id}")
    public String ficheEmprunt(@PathVariable int id, Model model) {
        EmpruntBean emprunt = empruntProxy.cherhcerUnEmprunt(id);
        model.addAttribute("emprunt", emprunt);
        return "FicheEmprunt";
    }

    @RequestMapping("/details-empruntEncours/{id}")
    public String ficheEmpruntEncours(@PathVariable int id, Model model) {
        EmpruntBean emprunt = empruntProxy.cherhcerUnEmprunt(id);
        model.addAttribute("emprunt", emprunt);
        return "FicheEmpruntEncours";
    }

    @RequestMapping("/retours/Emprunt/{id}")
    public String retourLivre(@PathVariable int id, Model model) {
        empruntProxy.retirerUnLivreEmprunter(id);
        return "RetournerLivre";
    }

    @RequestMapping(value = "/Emprunts/lecteurs")
    public String EmpruntLecteurs(Model model){
        List<EmpruntBean> emprunts =  empruntProxy.listeEmprunt();
        model.addAttribute("emprunts", emprunts);
        return "RechercherUnLecteur";
    }

    @RequestMapping("/details-lecteurEmprunt/{lecteur}")
    public String ficheLecteurEmprunt(@PathVariable String lecteur, Model model) {
        List<EmpruntBean> emprunts =  empruntProxy.livreEmprunterParLecteur(lecteur);
        model.addAttribute("emprunts", emprunts);
        return "LivreEmprunterParUnLecteur";
    }

}
