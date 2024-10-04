public class Cliente extends Pessoa{
    private int codigo;
    
    public Cliente(String nome, int dia, int mes, int ano, int codigo){
        super(nome, dia, mes, ano);
        setCodigo(codigo);
    }
    
    @Override
    public void imprimeDados(){
        System.out.println("--------------------------------------------------");
        System.out.println("*Cliente*");
        System.out.println("Nome: " + getNome());
        System.out.println("Data de Nascimento: " + getData().formataData());
        System.out.println("Codigo: " + getCodigo());
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
}
