import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CalculoCamisas {
    public static void main(String[] args) {
        String arquivo = "camisas.txt";
        String linha;

        int totalCamisasVendidas = 0;
        double valorTotalVenda = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); 
            try (FileWriter fw = new FileWriter("saida.txt")) {
                fw.write("Nome do Cliente | Qtd Comprada | Valor Aplicado | Valor Venda\n");
                while ((linha = br.readLine()) != null) {
                    String[] valores = linha.split(",");
                    String nomeCliente = valores[0];
                    int qtdComprada = Integer.parseInt(valores[1]);

                    double valorAplicado = (qtdComprada < 12) ? 10.83 : 10.00;
                    double valorVenda = qtdComprada * valorAplicado;

                    totalCamisasVendidas += qtdComprada;
                    valorTotalVenda += valorVenda;

                    fw.write(nomeCliente + " | " + qtdComprada + " | " + valorAplicado + " | " + valorVenda + "\n");
                }
                fw.write("Total de Camisas Vendidas: " + totalCamisasVendidas + "\n");
                fw.write("Valor Total da Venda: R$ " + String.format("%.2f", valorTotalVenda) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}