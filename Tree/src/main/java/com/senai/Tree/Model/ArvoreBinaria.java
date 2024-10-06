package com.senai.Tree.Model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArvoreBinaria {
    private Node raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    // Método para contar o número de nós da árvore
    public int contarNos(Node nodo) {
        if (nodo == null) {
            return 0;
        }

        // Conta o nó atual + os nós da subárvore esquerda + os nós da subárvore direita
        return 1 + contarNos(nodo.esquerda) + contarNos(nodo.direita);
    }


    // Método para contar o número de nós folha
    public int contarNosFolha(Node nodo) {
        if (nodo == null) {
            return 0;
        }

        // Se o nó não tem filhos, é uma folha
        if (nodo.esquerda == null && nodo.direita == null) {
            return 1;
        }

        // Soma os nós folha das subárvores esquerda e direita
        return contarNosFolha(nodo.esquerda) + contarNosFolha(nodo.direita);
    }

    // Método para contar o número de nós não folha (com pelo menos um filho)
    public int contarNosNaoFolha(Node nodo) {
        if (nodo == null) {
            return 0;
        }

        // Se o nó tem pelo menos um filho, conta como nó não folha
        if (nodo.esquerda != null || nodo.direita != null) {
            return 1 + contarNosNaoFolha(nodo.esquerda) + contarNosNaoFolha(nodo.direita);
        }

        // Caso contrário, não conta o nó (ele é folha)
        return 0;
    }

    // Método para calcular o grau de um nó (quantos filhos ele possui)
    private int calcularGrau(Node nodo) {
        if (nodo == null) {
            return 0;
        }

        int grau = 0;

        if (nodo.esquerda != null) {
            grau++;
        }

        if (nodo.direita != null) {
            grau++;
        }

        return grau;
    }

    // Método para calcular o grau máximo da árvore binária (o maior número de filhos de qualquer nó)
    public int grauMaximo(Node nodo) {
        if (nodo == null) {
            return 0;
        }

        // Grau do nó atual
        int grauAtual = calcularGrau(nodo);

        // Grau máximo da subárvore esquerda e direita
        int grauEsquerda = grauMaximo(nodo.esquerda);
        int grauDireita = grauMaximo(nodo.direita);

        // Retorna o maior grau encontrado
        return Math.max(grauAtual, Math.max(grauEsquerda, grauDireita));
    }

    // Método para obter o número de nós, a partir da raiz
    public int tamanho() {
        return contarNos(raiz);
    }

    // Método para obter o número de nós folha, a partir da raiz
    public int contarFolhas() {
        return contarNosFolha(raiz);
    }

    // Método para obter o número de nós não folha
    public int contarNaoFolhas() {
        return contarNosNaoFolha(raiz);
    }

    // Método para obter o grau máximo a partir da raiz
    public int obterGrauMaximo() {
        return grauMaximo(raiz);
    }

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private Node inserirRec(Node raiz, int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = inserirRec(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = inserirRec(raiz.direita, valor);
        }

        return raiz;
    }

    // Método para deletar um nó
    public void deletar(int valor) {
        raiz = deletarRec(raiz, valor);
    }

    private Node deletarRec(Node raiz, int valor) {
        if (raiz == null) return raiz;

        if (valor < raiz.valor) {
            raiz.esquerda = deletarRec(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = deletarRec(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null)
                return raiz.direita;
            else if (raiz.direita == null)
                return raiz.esquerda;

            raiz.valor = valorMinimo(raiz.direita);
            raiz.direita = deletarRec(raiz.direita, raiz.valor);
        }

        return raiz;
    }

    private int valorMinimo(Node raiz) {
        int minimo = raiz.valor;
        while (raiz.esquerda != null) {
            minimo = raiz.esquerda.valor;
            raiz = raiz.esquerda;
        }
        return minimo;
    }

    // Método para exibir a árvore em Pré-Ordem (Nó, Esquerda, Direita)
    public void exibirPreOrdem() {
        exibirPreOrdemRec(raiz);
        System.out.println();
    }

    private void exibirPreOrdemRec(Node node) {
        if (node != null) {
            System.out.print(node.valor + " ");
            exibirPreOrdemRec(node.esquerda);
            exibirPreOrdemRec(node.direita);
        }
    }


}

