public class CadastroPessoas{
    private int qtdAtual;
    private int qtdTotal;
    private Pessoa[] pessoas;
    
    public CadastroPessoas(int qtdTotal){
        setQtdAtual(0);
        setQtdTotal(qtdTotal);
        this.pessoas = new Pessoa[qtdTotal];
    }
    
    public void cadastraPessoa(Pessoa pess){
        if(getQtdAtual() >= getQtdTotal()){
            System.out.println("--------------------------------------------------");
            System.out.println("Cadastro Cheio");
        } else {
            this.pessoas[getQtdAtual()] = pess;
            setQtdAtual(getQtdAtual() + 1);
        }
    }
    
    public void imprimeCadastro(){
        for(int i = 0; i < getQtdAtual(); i++){
            pessoas[i].imprimeDados();
        }
    }
    
    public void removePessoaPorNome(String nome){
        boolean pessoaEncontrada = false;
        for (int i = 0; i < getQtdAtual(); i++){
            if (pessoas[i].getNome().equalsIgnoreCase(nome)){
                pessoaEncontrada = true;
                for (int j = i; j < getQtdAtual() - 1; j++){
                    pessoas[j] = pessoas[j + 1];
                }
                pessoas[getQtdAtual() - 1] = null;
                setQtdAtual(getQtdAtual() - 1);
                System.out.println(nome + " removido do cadastro.");
                break;
            }
        }
        if (!pessoaEncontrada) {
            System.out.println("Pessoa não encontrada.");
        }
    }
    
    public void setQtdTotal(int qtdTotal){
        this.qtdTotal = qtdTotal;
    }
    
    public int getQtdTotal(){
        return this.qtdTotal;
    }
    
    public void setQtdAtual(int qtdAtual){
        this.qtdAtual = qtdAtual;
    }
    
    public int getQtdAtual(){
        return this.qtdAtual;
    }
    
}
