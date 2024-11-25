package se.jobsesprit.interfaces;

import java.util.List;

public interface InterfaceCRUD<T> {
    public void ajouterEntities(T t);
    public List<T> listeDesEntities();
    public void modifierEntities(T t);
    public void supprimerEntities(T t);
}

