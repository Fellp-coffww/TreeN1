package com.senai.Tree.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    int valor;
    Node esquerda, direita;

    public Node(int valor) {
        this.valor = valor;
        esquerda = direita = null;
    }
}
