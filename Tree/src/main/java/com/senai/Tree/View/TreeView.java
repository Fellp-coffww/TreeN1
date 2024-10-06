package com.senai.Tree.View;

import com.senai.Tree.Controller.TreeController;
import com.senai.Tree.Model.ArvoreBinaria;
import lombok.Getter;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("TreeView")
@ManagedBean
@Getter
public class TreeView {

    DefaultDiagramModel model;

    ArvoreBinaria arvoreBinaria = new ArvoreBinaria();

    int treeSize;

    int leafNodes;

    int nonLeafNodes;

    int degree;

    @PostConstruct
    public void init() {

      /*  model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);

        Element elementA = new Element("A", "20em", "6em");
        elementA.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

        Element elementB = new Element("B", "10em", "18em");
        elementB.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));

        Element elementC = new Element("C", "40em", "18em");
        elementC.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));

        model.addElement(elementA);
        model.addElement(elementB);
        model.addElement(elementC);

        model.connect(new Connection(elementA.getEndPoints().get(0), elementB.getEndPoints().get(0)));
        model.connect(new Connection(elementA.getEndPoints().get(0), elementC.getEndPoints().get(0)));
    */

        arvoreBinaria.inserir(50);
        arvoreBinaria.inserir(20);
        arvoreBinaria.inserir(15);
        arvoreBinaria.inserir(30);
        arvoreBinaria.inserir(40);
        arvoreBinaria.inserir(80);
        arvoreBinaria.inserir(85);

        treeSize = arvoreBinaria.tamanho();
        leafNodes = arvoreBinaria.contarFolhas();
        nonLeafNodes = arvoreBinaria.contarNaoFolhas();
        degree = arvoreBinaria.grauMaximo(arvoreBinaria.getRaiz());

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        TreeController treeController = new TreeController();
        treeController.arvoreBinariaDiagramAdapter(arvoreBinaria.getRaiz(), model, 50, 2, 0, 27);
    }

    public DiagramModel getModel() {
        return model;
    }

    public void confirm() {
        addMessage("Confirmed", "You have accepted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
