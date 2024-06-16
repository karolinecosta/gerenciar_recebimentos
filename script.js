function executeProgram(questionNumber) {
    let outputArea = document.getElementById('outputArea');
    outputArea.textContent = 'Executando Questão ' + questionNumber + '...';

    fetch(`/execute`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ question: questionNumber }),
    })
    .then(response => response.text())
    .then(result => {
        outputArea.textContent = result;
    })
    .catch(error => {
        outputArea.textContent = 'Erro ao executar o programa: ' + error;
    });
}