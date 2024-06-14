import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CalculoXadrez {
    public static void main(String[] args) {
        String arquivo = "xadrez.txt";
        String linha;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // Pular a primeira linha de cabeçalho
            try (FileWriter fw = new FileWriter("saida.txt")) {
                fw.write("Núm. Partida | Início | Término | Tempo Total\n");
                while ((linha = br.readLine()) != null) {
                    String[] valores = linha.split(",");
                    int numPartida = Integer.parseInt(valores[0]);
                    LocalTime inicio = LocalTime.parse(valores[1], formatter);
                    LocalTime termino = LocalTime.parse(valores[2], formatter);

                    Duration duracao = Duration.between(inicio, termino);
                    String tempoTotal;

                    if (duracao.toHours() > 12) {
                        tempoTotal = "Jogo considerado empatado";
                    } else {
                        long horas = duracao.toHours();
                        long minutos = duracao.toMinutes() % 60;
                        tempoTotal = String.format("%d:%02d", horas, minutos);
                    }

                    fw.write(numPartida + " | " + inicio + " | " + termino + " | " + tempoTotal + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}