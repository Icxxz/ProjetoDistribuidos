public interface Reclamacao {
    void registrarReclamacao(String motivo);
    String consultarStatusReclamacao();
}