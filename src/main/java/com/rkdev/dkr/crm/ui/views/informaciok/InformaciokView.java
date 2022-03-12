package com.rkdev.dkr.crm.ui.views.informaciok;

import com.rkdev.dkr.crm.ui.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("DKR | Információk")
@Route(value = "", layout = MainLayout.class)
public class InformaciokView extends VerticalLayout {

    public InformaciokView() {

        Image image = new Image("http://rbertkiss.nhely.hu/assets/img/appimage.png", "AlkalmazásIkon");
        image.setWidth("50%");

        H1 aboutlabel = new H1("DKR | DOKUMENTUM KEZELŐ RENDSZER");

        Dialog dialog = new Dialog();
        dialog.add(new Text("Developer: Kiss Róbert | Copyright © 2021"));
        dialog.setWidth("480px");
        dialog.setHeight("70px");

        Span rendszer = new Span("A rendszer a Vaadin keretrendszeren alapuló webapp. A rendszer az IntelliJ IDEA fejlesztői környezettel készült. A webalkalmazás képes a PWA telepítésre. Az adatbázist egy MySQL adatbázisszerver" +
                " biztosítja.");
        rendszer.getElement().getStyle().set("align", "justify");


        Dialog rdialog = new Dialog();
        rdialog.add(new VerticalLayout(new H3("Rendszerinformációk"), rendszer));
        rdialog.setWidth("490px");
        rdialog.setHeight("400px");

        Button diaBtn = new Button("FEJLESZTŐI INFORMÁCIÓK", event -> dialog.open());
        Button nfoBtn = new Button("RENDSZER INFORMÁCIÓK", event -> rdialog.open());
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(image, aboutlabel, new HorizontalLayout(diaBtn, nfoBtn));
        addClassName("informaciok-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }


}
