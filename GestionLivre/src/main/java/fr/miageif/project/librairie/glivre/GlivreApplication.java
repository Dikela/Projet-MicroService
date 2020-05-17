package fr.miageif.project.librairie.glivre;

import ch.qos.logback.core.net.SyslogOutputStream;
import fr.miageif.project.librairie.glivre.modelBean.Lecteur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class GlivreApplication {
    public static void main(String[] args) {
        /*Date newDate = null;
        try {
            newDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-02-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Lecteur l1 = new Lecteur(2, 'M', "Nguyen", "Michel", newDate, "4 Rue Denis Diderot Garges-les-Gonesse");
        System.out.println(l1);*/
        SpringApplication.run(GlivreApplication.class, args);

    }
}
