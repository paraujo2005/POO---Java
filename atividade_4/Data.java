public class Data {
    private int dia;
    private int mes;
    private int ano;
    
    public Data(int dia, int mes, int ano){
        setDia(dia);
        setMes(mes);
        setAno(ano);
    }
    
    public String formataData(){
        String data_formatada;
        if(getDia() < 10 && getMes() < 10) {
            data_formatada = "0" + getDia() + "/0" + getMes() + "/" + getAno();
        } else if(getDia() < 10){
            data_formatada = "0" + getDia() + "/" + getMes() + "/" + getAno();
        } else if(getMes() < 10) {
            data_formatada = getDia() + "/0" + getMes() + "/" + getAno();
        } else {
            data_formatada = getDia() + "/" + getMes() + "/" + getAno();
        }
        
        return data_formatada;
    }
    
    public int getDia(){
        return this.dia;
    }
    
    public void setDia(int dia){
        this.dia = dia;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public void setMes(int mes){
        this.mes = mes;
    }
    
    public int getAno(){
        return this.ano;
    }
    
    public void setAno(int ano){
        this.ano = ano;
    }
    
}
