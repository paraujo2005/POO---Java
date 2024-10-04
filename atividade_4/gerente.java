public class Gerente extends Funcionario{
    private String area;
    
    public Gerente(String nome, int dia, int mes, int ano, float salario, String area){
        super(nome, dia, mes, ano, salario);
        setArea(area);
    }
    
    @Override
    public void imprimeDados(){
        System.out.println("--------------------------------------------------");
        System.out.println("*Funcionario*");
        System.out.println("Nome: " + getNome());
        System.out.println("Data de Nascimento: " + getData().formataData());
        System.out.println("Salario: R$" + getSalario());
        System.out.println("Imposto: R$" + calcuaImposto());
        System.out.println("Area: " + getArea());
    }
    
    public float calcuaImposto(){
        return (getSalario()/100) * 5;
    } 
    
    public String getArea(){
        return this.area;
    }
    
    public void setArea(String area){
        this.area = area;
    }
    
}