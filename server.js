const express = require('express');
const bodyParser = require('body-parser');
const { exec } = require('child_process');
const fs = require('fs');

const app = express();
const port = 3000;

app.use(express.static('web'));
app.use(bodyParser.json());

app.post('/execute', (req, res) => {
    const questionNumber = req.body.question;
    let command = '';
    let outputFile = '';

    switch (questionNumber) {
        case 1:
            command = 'javac questao1/TrocaValores.java && java -cp questao1 TrocaValores';
            outputFile = 'questao1/saida.txt';
            break;
        case 2:
            command = 'javac questao2/CalculoSalario.java && java -cp questao2 CalculoSalario';
            outputFile = 'questao2/saida.txt';
            break;
        case 3:
            command = 'javac questao3/CalculoCamisas.java && java -cp questao3 CalculoCamisas';
            outputFile = 'questao3/saida.txt';
            break;
        case 4:
            command = 'javac questao4/CalculoXadrez.java && java -cp questao4 CalculoXadrez';
            outputFile = 'questao4/saida.txt';
            break;
        default:
            res.status(400).send('Questão inválida');
            return;
    }

    exec(command, (error, stdout, stderr) => {
        if (error) {
            res.status(500).send(`Erro ao executar o programa: ${stderr}`);
            return;
        }
        fs.readFile(outputFile, 'utf8', (err, data) => {
            if (err) {
                res.status(500).send(`Erro ao ler o arquivo de saída: ${err}`);
                return;
            }
            res.send(data);
        });
    });
});

app.listen(port, () => {
    console.log(`Servidor rodando em http://localhost:${port}`);
});