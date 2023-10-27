package br.ufms.gabriel.costa.silva.patrimonio.backend.entidades;

public class ObjetoTipo {
    public int numPatrimonio;
    public String dataRegistro;
    public String nomeFuncionario;
    public String tipo;
    public String descricao;

    public int getNumPatrimonio() {
        return numPatrimonio;
    }

    @Override
    public String toString() {
        return "Patrimônio: " + numPatrimonio + "\n" +
                "Data de registro: " + dataRegistro + "\n" +
                "Nome do funcionario: " + nomeFuncionario + "\n" +
                "Tipo: " + tipo + "\n" +
                "Descrição do Tipo: " + descricao + '\'';
    }
}
