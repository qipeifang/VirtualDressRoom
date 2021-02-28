package restful.database;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EM {
    static final EntityManagerFactory emf =
                      Persistence.createEntityManagerFactory("sqlserver2008");
    private static final EntityManager entityManager = emf.createEntityManager();
    public static EntityManager getEntityManager(){
        synchronized (EntityManager.class){  
            if(entityManager.getTransaction().isActive() && 
                               entityManager.getTransaction().getRollbackOnly())
                                          entityManager.getTransaction().rollback();
            if(!entityManager.getTransaction().isActive()) 
                                          entityManager.getTransaction().begin();
        }
        return entityManager;
    }
}