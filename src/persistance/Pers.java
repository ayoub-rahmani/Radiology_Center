package persistance;

import entity.Personne;

public interface Pers {
    void add(Personne p);
    void remove(Personne p);
    Personne getPersonne(int id);
    Personne getAll();
    void update(int id);
}
