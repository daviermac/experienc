import com.gerenciador.gerenciadortarefas.model.Task;
import com.gerenciador.gerenciadortarefas.model.StatusTask;
import com.gerenciador.gerenciadortarefas.model.PrioridadeTask;
import com.gerenciador.gerenciadortarefas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> listarTodas() {
        return taskRepository.findAllOrderByPrioridadeAndDataCriacao();
    }
    
    public Optional<Task> buscarPorId(Long id) {
        return taskRepository.findById(id);
    }
    
    public Task salvar(Task task) {
        return taskRepository.save(task);
    }
    
    public void deletar(Long id) {
        taskRepository.deleteById(id);
    }
    
    public List<Task> buscarPorStatus(StatusTask status) {
        return taskRepository.findByStatus(status);
    }
    
    public List<Task> buscarPorPrioridade(PrioridadeTask prioridade) {
        return taskRepository.findByPrioridade(prioridade);
    }
    
    public List<Task> buscarPorTermo(String termo) {
        return taskRepository.findByTituloContainingOrDescricaoContaining(termo);
    }
    
    public Task marcarComoConcluida(Long id) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setStatus(StatusTask.CONCLUIDA);
            return taskRepository.save(task);
        }
        return null;
    }
    
    public long contarPorStatus(StatusTask status) {
        return taskRepository.findByStatus(status).size();
    }
}
