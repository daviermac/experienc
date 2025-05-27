public enum PrioridadeTask {
    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");
    
    private final String descricao;
    
    PrioridadeTask(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
