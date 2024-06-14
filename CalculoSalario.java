import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CalculoSalario {
    public static void main(String[] args) {
        String arquivo = "vendedores.txt";
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); 
            try (FileWriter fw = new FileWriter("saida.txt")) {
                fw.write("Vendedor | Qtd Itens | Valor Vendido | Valor Comissão | Salário | Salário Final\n");
                while ((linha = br.readLine()) != null) {
                    String[] valores = linha.split(",");
                    String nome = valores[0];
                    int qtdItens = Integer.parseInt(valores[1]);
                    double valorVendido = Double.parseDouble(valores[2]);
                    double salarioFixo = Double.parseDouble(valores[3]);
                    double comissaoFixa = Double.parseDouble(valores[4]);

                    double valorComissao = comissaoFixa + 0.05 * valorVendido;
                    double salarioFinal = salarioFixo + valorComissao;

                    fw.write(nome + " | " + qtdItens + " | " + valorVendido + " | " + valorComissao + " | " + salarioFixo + " | " + salarioFinal + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}