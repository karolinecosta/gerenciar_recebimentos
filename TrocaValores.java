import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TrocaValores {
    public static void main(String[] args) {
        String arquivo = "variaveis.txt";
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // Skip the header line
            linha = br.readLine(); // Read the data line
            if (linha != null) {
                String[] valores = linha.split(",");
                int a = Integer.parseInt(valores[0].trim());
                int b = Integer.parseInt(valores[1].trim());

                // Trocar os valores
                int temp = a;
                a = b;
                b = temp;

                // Gerar arquivo de saída
                try (FileWriter fw = new FileWriter("saida.txt")) {
                    fw.write("Valor Origem de A | Valor Origem de B | Valor Final de A | Valor Final de B\n");
                    fw.write(valores[0].trim() + " " + valores[1].trim() + " " + a + " " + b + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
