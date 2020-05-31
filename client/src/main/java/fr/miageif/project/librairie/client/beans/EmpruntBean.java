package fr.miageif.project.librairie.client.beans;

public class EmpruntBean {

    public Integer id;
    public String ISBN;
    public String lecteur;
    public String datepret;
    public String dateretour;

    public EmpruntBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLecteur() {
        return lecteur;
    }

    public void setLecteur(String lecteur) {
        this.lecteur = lecteur;
    }

    public String getDatepret() {
        return datepret;
    }

    public void setDatepret(String datepret) {
        this.datepret = datepret;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String dateretour) {
        this.dateretour = dateretour;
    }

    @Override
    public String toString(){
        return "A la date du " + this.datepret + " un exemplaire du livre " +
                "identifiés par l'ISBN " + this.ISBN + " , a été emprunté par" +
                "le lecteur identifié " + this.lecteur + " et a été rendu le " + this.dateretour;
    }
}
