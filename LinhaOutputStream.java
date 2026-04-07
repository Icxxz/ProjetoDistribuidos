import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LinhaOutputStream extends OutputStream {
    private Linha[] dados;
    private int numObjetos;
    private DataOutputStream out;

    // a) Construtor com array, número de objetos e OutputStream de destino
    public LinhaOutputStream(Linha[] dados, int numObjetos, OutputStream destino) {
        this.dados = dados;
        this.numObjetos = numObjetos;
        this.out = new DataOutputStream(destino);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    // Regra iii: Enviar número de bytes gravados + pelo menos 3 atributos
    public void enviarDados() throws IOException {
        for (int i = 0; i < numObjetos; i++) {
            Linha linha = dados[i];
            
            // 4 bytes (int) + 4 bytes (int) + 8 bytes (double) = 16 bytes
            int bytesUtilizados = 16; 
            
            out.writeInt(bytesUtilizados); // Grava quantos bytes vão a seguir
            out.writeInt(linha.ddd);
            out.writeInt(linha.numeroDaLinha);
            out.writeDouble(linha.valorPlano);
        }
        out.flush();
    }
}