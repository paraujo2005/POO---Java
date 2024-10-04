public abstract class Pessoa {
    private String nome;
    private Data data;
    
    public Pessoa(String nome, int dia, int mes, int ano){
        setNome(nome);
        Data data = new Data(dia, mes, ano);
        setData(data);
    }
    
    abstract void imprimeDados();
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public Data getData(){
        return this.data;
    }
    
    public void setData(Data data){
        this.data = data;
    }
}
