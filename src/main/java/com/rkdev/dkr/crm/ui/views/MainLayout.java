package com.rkdev.dkr.crm.ui.views;

import com.rkdev.dkr.crm.ui.views.informaciok.InformaciokView;
import com.rkdev.dkr.crm.ui.views.list.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.lumo.Lumo;

@Route
@PageTitle("DKR | 2021")
@CssImport("./styles/shared-styles.css")
@PWA(name = "DOKUMENTUM KEZELÖ RENDSZER",
        shortName = "DKR", offlineResources = {"./styles/offline.css"}, enableInstallPrompt = true)

public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        Button databaseBtn = new Button("Adatbázis", new Icon(VaadinIcon.DATABASE));
        databaseBtn.addClickListener(e -> databaseBtn.getUI().ifPresent(ui -> ui.navigate(MainView.class)));

        Button infoBtn = new Button("Kezdőlap", new Icon(VaadinIcon.HOME));
        infoBtn.addClickListener(e -> infoBtn.getUI().ifPresent(ui -> ui.navigate(InformaciokView.class)));


        Button modeBtn = new Button(new Icon(VaadinIcon.MOON), click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();
            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });
        Label lbl = new Label("     ");
        addToNavbar(new HorizontalLayout(
                infoBtn, databaseBtn, modeBtn, lbl
        ));

    }

    private void createHeader() {
        H1 logo = new H1("DKR | DOKUMENTUM KEZELŐ RENDSZER");
        logo.addClassName("logo");


        HorizontalLayout header = new HorizontalLayout(logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
    }
}
