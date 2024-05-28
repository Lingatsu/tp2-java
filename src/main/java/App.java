import domain.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("Hello");
        Livre newLivre = new Livre();
        newLivre.setAuteur("Test");
        newLivre.setTitre("Voyage");
        em.persist(newLivre);
        // Etape 1 afficher
        Livre livre = em.find(Livre.class,2);
        if (null != livre) {
            System.out.println(livre);
        }
        // Etape 3 modifier
        Livre livreAModifier = em.find(Livre.class,5);
        if (null != livreAModifier) {
            livreAModifier.setTitre("Du plaisir dans la cuisine");
        }
        // Etape 4 Supprimer
        Livre livreASupprimer = em.find(Livre.class,6);
        if (null != livreASupprimer) {
            em.remove(livreASupprimer);
        }
        // Etape 5 lister livres
        TypedQuery<Livre> query = em.createQuery("select l from Livre l", Livre.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
