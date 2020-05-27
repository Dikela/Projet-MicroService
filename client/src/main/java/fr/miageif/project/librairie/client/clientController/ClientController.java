package fr.miageif.project.librairie.client.clientController;

import fr.miageif.project.librairie.client.beans.LivreBean;
import fr.miageif.project.librairie.client.proxies.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientController {

    @Autowired()
    private ClientProxy clientProxy;

    @RequestMapping("/")
    public String acceuil(Model model){
        List<LivreBean> livres =  clientProxy.listeLivres();
        model.addAttribute("livres", livres);

        return "Accueil";
    }
}
