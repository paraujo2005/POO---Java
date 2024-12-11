package org.example;

public class Usuario {
    private String cpf;
    private String senha;
    private String email;
    private String empresa;

    public Usuario(String cpf, String senha, String email, String empresa) {
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.empresa = empresa;
    }

    //Sets e Gets
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
