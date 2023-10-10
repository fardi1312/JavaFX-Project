package se.jobsesprit.interfaces;

import java.util.List;

public interface InterfaceOffreur<T> {
    public void ajouterOffreur(T t);
    public List<T> listeDesOffreurs();
    public void modifierOffreur(T t); 
    public T rechercherOffreur(int id); 
    public void supprimerOffreur(int id);
}

