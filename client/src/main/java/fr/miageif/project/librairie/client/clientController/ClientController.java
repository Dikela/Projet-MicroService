package fr.miageif.project.librairie.client.clientController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

    @RequestMapping("/")
    public String acceuil(Model model){
        return "Accueil";
    }
}
