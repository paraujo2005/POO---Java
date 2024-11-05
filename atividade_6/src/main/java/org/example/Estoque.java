package org.example;

import java.util.HashSet;
import java.util.Set;


public class Estoque {
    private Set<Produto> produtos;

    public Estoque() {
        this.produtos = new HashSet<>();
    }

    public boolean adicionarProduto(Produto produto) {
        return produtos.add(produto);
    }

    public boolean removerProduto(String nome) {
        return produtos.removeIf(produto -> produto.getNome().equals(nome));
    }

    public Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }

    public Set<Produto> listarProdutos() {
        return new HashSet<>(produtos);
    }
}
