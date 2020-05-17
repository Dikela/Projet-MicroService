package fr.miageif.project.librairie.glivre.modelBean;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Lecteur {
    @Id
    private int id;

    @Column(name = "genre")
    private char genre;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date_naissance;

    @Column(name = "adresse")
    private String adresse;

    public Lecteur() {

    }

    public Lecteur(int id, char genre, String nom, String prenom, Date date_naissance, String adresse) {
        this.id = id;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString(){
        String monsieurOuMadame = this.genre == 'M' ? "Monsieur" : "Madame";
        SimpleDateFormat formater = new SimpleDateFormat("d MMMM yyyy");

        return "Le lecteur d'identifiant " + this.id + " correspond à " + monsieurOuMadame + " " + this.prenom
                + " " + this.nom + " né le "+  formater.format(this.date_naissance) + " et domicilié " + this.adresse;
    }
}
