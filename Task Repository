import com.gerenciador.gerenciadortarefas.model.Task;
import com.gerenciador.gerenciadortarefas.model.StatusTask;
import com.gerenciador.gerenciadortarefas.model.PrioridadeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByStatus(StatusTask status);
    
    List<Task> findByPrioridade(PrioridadeTask prioridade);
    
    @Query("SELECT t FROM Task t WHERE t.titulo LIKE %?1% OR t.descricao LIKE %?1%")
    List<Task> findByTituloContainingOrDescricaoContaining(String termo);
    
    @Query("SELECT t FROM Task t ORDER BY t.prioridade DESC, t.dataCriacao ASC")
    List<Task> findAllOrderByPrioridadeAndDataCriacao();
}
