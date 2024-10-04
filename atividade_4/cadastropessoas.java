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