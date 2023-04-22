function GerarMatricula() {

    const dataAtual = new Date();
    const anoAtual = dataAtual.getFullYear();

    let txt = "ACA";
    let aleatorio = Math.floor(Math.random() * 1500);
    console.log(aleatorio);
    document.getElementById('matricula').value = (txt + anoAtual + aleatorio);

}