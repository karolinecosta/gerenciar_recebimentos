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
            command = 'javac TrocaValores.java && java -cp TrocaValores';
            outputFile = 'saida.txt';
            break;
        case 2:
            command = 'javac CalculoSalario.java && java -cp CalculoSalario';
            outputFile = 'saida.txt';
            break;
        case 3:
            command = 'javac CalculoCamisas.java && java -cp CalculoCamisas';
            outputFile = 'saida.txt';
            break;
        case 4:
            command = 'javac CalculoXadrez.java && java -cp CalculoXadrez';
            outputFile = 'saida.txt';
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
