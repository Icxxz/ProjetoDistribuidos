import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TesteStreams {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Criando array de objetos para teste
        Linha[] linhas = new Linha[2];
        linhas[0] = new Linha();
        linhas[0].ddd = 85; // Ceará
        linhas[0].numeroDaLinha = 988887777;
        linhas[0].valorPlano = 49.90;

        linhas[1] = new Linha();
        linhas[1].ddd = 11; // São Paulo
        linhas[1].numeroDaLinha = 977776666;
        linhas[1].valorPlano = 89.90;

        int numObjetos = 2;

        System.out.println("--- 1. TESTE EM ARQUIVO (File I/O) ---");
        // Escrita em arquivo
        FileOutputStream fos = new FileOutputStream("dados_linha.dat");
        LinhaOutputStream outArquivo = new LinhaOutputStream(linhas, numObjetos, fos);
        outArquivo.enviarDados();
        fos.close();
        
        // Leitura em arquivo
        FileInputStream fis = new FileInputStream("dados_linha.dat");
        LinhaInputStream inArquivo = new LinhaInputStream(fis);
        inArquivo.lerDados(numObjetos);
        fis.close();

        System.out.println("\n--- 2. TESTE EM SERVIDOR REMOTO (TCP) ---");
        // Criando uma Thread rápida para simular o Servidor
        Thread servidor = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(9090)) {
                Socket clienteSocket = serverSocket.accept();
                // Lê os dados recebidos via TCP
                LinhaInputStream inRede = new LinhaInputStream(clienteSocket.getInputStream());
                inRede.lerDados(numObjetos);
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        servidor.start();

        // Dando um tempinho para o servidor iniciar
        Thread.sleep(500); 

        // Cliente conectando e enviando dados via TCP
        Socket socket = new Socket("localhost", 9090);
        LinhaOutputStream outRede = new LinhaOutputStream(linhas, numObjetos, socket.getOutputStream());
        outRede.enviarDados();
        socket.close();
        
        // Aguarda a thread do servidor finalizar
        servidor.join(); 

        System.out.println("\n--- 3. TESTE NO CONSOLE (System.out / System.in) ---");
        System.out.println("Atenção: Os dados impressos em System.out aparecerão como símbolos ilegíveis pois estão em formato binário.");
        
        // Teste System.out (O terminal mostrará lixo de memória / binário puro)
        LinhaOutputStream outConsole = new LinhaOutputStream(linhas, numObjetos, System.out);
        outConsole.enviarDados();
        System.out.println("\n(Fim da impressão binária)\n");

        /* // Teste System.in 
        // Isso travaria o console esperando você digitar bytes puros (o que é impossível via teclado normal).
        // Na prática, a implementação seria:
        System.out.println("Tentando ler do System.in...");
        LinhaInputStream inConsole = new LinhaInputStream(System.in);
        inConsole.lerDados(numObjetos);
        */
    }
}