import com.gerenciador.gerenciadortarefas.model.Task;
import com.gerenciador.gerenciadortarefas.model.StatusTask;
import com.gerenciador.gerenciadortarefas.model.PrioridadeTask;
import com.gerenciador.gerenciadortarefas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public String listarTarefas(Model model, @RequestParam(required = false) String filtro) {
        List<Task> tasks;
        
        if (filtro != null && !filtro.isEmpty()) {
            switch (filtro) {
                case "pendente":
                    tasks = taskService.buscarPorStatus(StatusTask.PENDENTE);
                    break;
                case "andamento":
                    tasks = taskService.buscarPorStatus(StatusTask.EM_ANDAMENTO);
                    break;
                case "concluida":
                    tasks = taskService.buscarPorStatus(StatusTask.CONCLUIDA);
                    break;
                case "alta":
                    tasks = taskService.buscarPorPrioridade(PrioridadeTask.ALTA);
                    break;
                default:
                    tasks = taskService.listarTodas();
            }
        } else {
            tasks = taskService.listarTodas();
        }
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("totalPendentes", taskService.contarPorStatus(StatusTask.PENDENTE));
        model.addAttribute("totalAndamento", taskService.contarPorStatus(StatusTask.EM_ANDAMENTO));
        model.addAttribute("totalConcluidas", taskService.contarPorStatus(StatusTask.CONCLUIDA));
        model.addAttribute("filtroAtual", filtro);
        
        return "index";
    }
    
    @GetMapping("/nova")
    public String novaTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("prioridades", PrioridadeTask.values());
        return "form";
    }
    
    @PostMapping("/salvar")
    public String salvarTask(@Valid @ModelAttribute Task task, BindingResult result, 
                           RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("prioridades", PrioridadeTask.values());
            return "form";
        }
        
        taskService.salvar(task);
        redirectAttributes.addFlashAttribute("sucesso", "Tarefa salva com sucesso!");
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editarTaskForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Task task = taskService.buscarPorId(id).orElse(null);
        if (task == null) {
            redirectAttributes.addFlashAttribute("erro", "Tarefa não encontrada!");
            return "redirect:/";
        }
        
        model.addAttribute("task", task);
        model.addAttribute("prioridades", PrioridadeTask.values());
        model.addAttribute("statusList", StatusTask.values());
        return "form";
    }
    
    @GetMapping("/deletar/{id}")
    public String deletarTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            taskService.deletar(id);
            redirectAttributes.addFlashAttribute("sucesso", "Tarefa deletada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao deletar tarefa!");
        }
        return "redirect:/";
    }
    
    @PostMapping("/concluir/{id}")
    public String concluirTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Task task = taskService.marcarComoConcluida(id);
        if (task != null) {
            redirectAttributes.addFlashAttribute("sucesso", "Tarefa marcada como concluída!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao concluir tarefa!");
        }
        return "redirect:/";
    }
    
    @GetMapping("/buscar")
    public String buscarTarefas(@RequestParam String termo, Model model) {
        List<Task> tasks = taskService.buscarPorTermo(termo);
        model.addAttribute("tasks", tasks);
        model.addAttribute("termoBusca", termo);
        model.addAttribute("totalPendentes", taskService.contarPorStatus(StatusTask.PENDENTE));
        model.addAttribute("totalAndamento", taskService.contarPorStatus(StatusTask.EM_ANDAMENTO));
        model.addAttribute("totalConcluidas", taskService.contarPorStatus(StatusTask.CONCLUIDA));
        return "index";
    }
}
