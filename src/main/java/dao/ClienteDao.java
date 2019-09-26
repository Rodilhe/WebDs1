package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Cliente;

/**
 *
 * @author dreges
 */
@Stateless
public class ClienteDao {

    @PersistenceContext
    EntityManager em;


    
    public void inserir(Cliente cliente) {
        em.persist(cliente);
    }
    
    public void alterar(Cliente cliente) {
        em.merge(cliente);
    }
    
    public void excluir(Cliente cliente) {
        em.remove(em.merge(cliente));
    }
    
    public List<Cliente> getClientes() {
        Query q = em.createQuery("select c from Cliente c order by c.nome");
        return q.getResultList();
    }
    
    public List<Cliente> getClientes(String nome) {
        Query q = em.createQuery("select c from Cliente c where c.nome = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }
    
}
