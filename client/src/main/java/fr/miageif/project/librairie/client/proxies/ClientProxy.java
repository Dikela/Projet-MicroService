package fr.miageif.project.librairie.client.proxies;

import fr.miageif.project.librairie.client.beans.LecteurBean;
import fr.miageif.project.librairie.client.beans.LivreBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "microservice-livre", url = "localhost:8003")
public interface ClientProxy {

    @GetMapping(value ="/Livres")
    List<LivreBean> listeLivres();

    @GetMapping(value = "/Livres/{isbn}")
    LivreBean afficherUnLivre(@PathVariable("isbn") String isbn);
}
