public abstract class Servico {
    protected String nomeDoServico;
    protected double valorMensalidade;
    protected boolean ativo;

    public Servico(String nomeDoServico, double valorMensalidade) {
        this.nomeDoServico = nomeDoServico;
        this.valorMensalidade = valorMensalidade;
        this.ativo = false;
    }

    // Métodos de negócio que as subclasses deverão implementar
    public abstract void ativar();
    public abstract void desativar();
    
    public String getNomeDoServico() {
        return nomeDoServico;
    }
}