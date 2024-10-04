public class Funcionario extends Pessoa{
    private float salario;
    
    public Funcionario(String nome, int dia, int mes, int ano, float salario){
        super(nome, dia, mes, ano);
        setSalario(salario);
    }
    
    @Override
    public void imprimeDados(){
        System.out.println("--------------------------------------------------");
        System.out.println("*Funcionario*");
        System.out.println("Nome: " + getNome());
        System.out.println("Data de Nascimento: " + getData().formataData());
        System.out.println("Salario: R$" + getSalario());
        System.out.println("Imposto: R$" + calcuaImposto());
    }
    
    public float calcuaImposto(){
        return (getSalario()/100) * 3;
    } 
    
    public float getSalario(){
        return this.salario;
    }
    
    public void setSalario(float salario){
        this.salario = salario;
    }
    
}