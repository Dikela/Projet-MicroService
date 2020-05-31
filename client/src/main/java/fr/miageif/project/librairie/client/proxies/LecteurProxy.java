package fr.miageif.project.librairie.client.proxies;

import fr.miageif.project.librairie.client.beans.LecteurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-lecteur", url = "localhost:8002")
public interface LecteurProxy {

    @GetMapping(value = "Lecteurs")
    List<LecteurBean> listeLecteurs();

    @GetMapping(value = "/Lecteurs/{id}")
    LecteurBean afficherUnLecteur(@PathVariable("id") int id);
}
