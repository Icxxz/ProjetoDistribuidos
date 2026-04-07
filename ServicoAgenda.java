public class ServicoAgenda extends Servico implements Reclamacao {
    private int limiteDeContatos;
    private int contatosSalvos;
    private String statusDaReclamacao;

    public ServicoAgenda(double valorMensalidade, int limiteDeContatos) {
        super("Agenda Inteligente", valorMensalidade);
        this.limiteDeContatos = limiteDeContatos;
        this.contatosSalvos = 0;
        this.statusDaReclamacao = "Sem reclamações";
    }

    public void adicionarContato() {
        if (this.ativo && contatosSalvos < limiteDeContatos) {
            contatosSalvos++;
            System.out.println("Contato adicionado na nuvem. Total: " + contatosSalvos);
        } else if (!this.ativo) {
            System.out.println("A agenda precisa estar ativa para salvar contatos.");
        } else {
            System.out.println("Limite da agenda atingido!");
        }
    }

    @Override
    public void ativar() {
        this.ativo = true;
        System.out.println("Agenda Inteligente sincronizada e ativada.");
    }

    @Override
    public void desativar() {
        this.ativo = false;
        System.out.println("Sincronização da Agenda desativada.");
    }

    // Implementação da Interface Reclamação
    @Override
    public void registrarReclamacao(String motivo) {
        this.statusDaReclamacao = "Em análise técnica";
        System.out.println("Reclamação técnica aberta para a Agenda. Motivo: " + motivo);
    }

    @Override
    public String consultarStatusReclamacao() {
        return this.statusDaReclamacao;
    }
}
