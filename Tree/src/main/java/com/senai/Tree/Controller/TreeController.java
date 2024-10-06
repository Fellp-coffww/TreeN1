package com.senai.Tree.Controller;

import com.senai.Tree.Model.ArvoreBinaria;
import com.senai.Tree.Model.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreeController {


    DefaultDiagramModel model = new DefaultDiagramModel();


    public void arvoreBinariaDiagramAdapter(Node node, DefaultDiagramModel model, int sizeX, int sizeY, int depth, int espacoInicial) {

        // Verifique se o nó é nulo
        if (node == null) {
            return;
        }

        // Se for a primeira chamada (raiz da árvore)
        if (model.getElements().size() == 0) {
            Element elementFather = new Element(node.getValor(), sizeX + "em", sizeY + "em");
            elementFather.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
            elementFather.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
            model.addElement(elementFather);
        }

        // Pega o último elemento como pai
        Element elementFather = model.getElements().get(model.getElements().size() - 1);
        elementFather.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

        // Incrementa o nível da árvore
        sizeY += 10; // Adiciona espaçamento vertical entre os níveis

        // Ajusta a largura de acordo com a profundidade da árvore
        int espacoHorizontal = espacoInicial / (depth + 1);

        // Cria e conecta o nó da esquerda
        if (node.getEsquerda() != null) {
            Element elementLeft = new Element(node.getEsquerda().getValor(), (sizeX - espacoHorizontal) + "em", sizeY + "em");
            elementLeft.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
            model.addElement(elementLeft);
            model.connect(new Connection(elementFather.getEndPoints().get(1), elementLeft.getEndPoints().get(0)));

            // Chama recursivamente para a subárvore esquerda, aumentando a profundidade
            arvoreBinariaDiagramAdapter(node.getEsquerda(), model, sizeX - espacoHorizontal, sizeY, depth + 1, espacoInicial);
        }

        // Cria e conecta o nó da direita
        if (node.getDireita() != null) {
            Element elementRight = new Element(node.getDireita().getValor(), (sizeX + espacoHorizontal) + "em", sizeY + "em");
            elementRight.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
            model.addElement(elementRight);
            model.connect(new Connection(elementFather.getEndPoints().get(1), elementRight.getEndPoints().get(0)));

            // Chama recursivamente para a subárvore direita, aumentando a profundidade
            arvoreBinariaDiagramAdapter(node.getDireita(), model, sizeX + espacoHorizontal, sizeY, depth + 1, espacoInicial);
        }
    }




}
