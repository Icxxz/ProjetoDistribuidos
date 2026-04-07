public class ServicoSigaMe extends Servico implements Reclamacao {
    private String numeroDestino;
    private String statusDaReclamacao;

    public ServicoSigaMe(double valorMensalidade) {
        super("Siga-me", valorMensalidade);
        this.statusDaReclamacao = "Sem reclamações";
    }

    public void configurarRedirecionamento(String numero) {
        this.numeroDestino = numero;
        System.out.println("Chamadas serão redirecionadas para: " + numero);
    }

    @Override
    public void ativar() {
        if (numeroDestino != null && !numeroDestino.isEmpty()) {
            this.ativo = true;
            System.out.println("Serviço Siga-me ativado com sucesso.");
        } else {
            System.out.println("Erro: Configure um número de destino antes de ativar o Siga-me.");
        }
    }

    @Override
    public void desativar() {
        this.ativo = false;
        System.out.println("Serviço Siga-me desativado.");
    }

    // Implementação da Interface Reclamação
    @Override
    public void registrarReclamacao(String motivo) {
        this.statusDaReclamacao = "Pendente";
        System.out.println("Reclamação registrada no serviço Siga-me. Motivo: " + motivo);
    }

    @Override
    public String consultarStatusReclamacao() {
        return this.statusDaReclamacao;
    }
}
