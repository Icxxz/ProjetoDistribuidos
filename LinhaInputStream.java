import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LinhaInputStream extends InputStream {
    private DataInputStream in;

    // a) Construtor recebendo InputStream de origem
    public LinhaInputStream(InputStream origem) {
        this.in = new DataInputStream(origem);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    // Lê os dados respeitando o protocolo definido na saída
    public void lerDados(int numObjetos) throws IOException {
        for (int i = 0; i < numObjetos; i++) {
            int bytesUtilizados = in.readInt();
            int ddd = in.readInt();
            int numeroDaLinha = in.readInt();
            double valorPlano = in.readDouble();
            
            System.out.println("-> Lido [Tamanho: " + bytesUtilizados + " bytes] | Linha: (" 
                               + ddd + ") " + numeroDaLinha + " | Valor: R$ " + valorPlano);
        }
    }
}