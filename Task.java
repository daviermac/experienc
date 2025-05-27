import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;
    
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    @Column(length = 500)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTask status = StatusTask.PENDENTE;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadeTask prioridade = PrioridadeTask.MEDIA;
    
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    
    // Construtores
    public Task() {
        this.dataCriacao = LocalDateTime.now();
    }
    
    public Task(String titulo, String descricao, PrioridadeTask prioridade) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public StatusTask getStatus() { return status; }
    public void setStatus(StatusTask status) { 
        this.status = status;
        if (status == StatusTask.CONCLUIDA) {
            this.dataConclusao = LocalDateTime.now();
        }
    }
    
    public PrioridadeTask getPrioridade() { return prioridade; }
    public void setPrioridade(PrioridadeTask prioridade) { this.prioridade = prioridade; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public LocalDateTime getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDateTime dataConclusao) { this.dataConclusao = dataConclusao; }
}
