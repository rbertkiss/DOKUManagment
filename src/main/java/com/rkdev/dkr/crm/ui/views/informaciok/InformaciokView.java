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
import com.vaadin.flow.server.VaadinService;

@PageTitle("DKR | Információk")
@Route(value = "", layout = MainLayout.class)
public class InformaciokView extends VerticalLayout {

    public InformaciokView() {

        Image image = new Image("https://doc-08-2k-docs.googleusercontent.com/docs/securesc/lf3dv7chaafb814n916kjgig2tpf9al0/hii2b9linf5a3pu039hgsk7vav3gsee3/1649586000000/10093904045815650472/10093904045815650472/1goGryW8FOglg2FLrkqL1FkWASu90XgBp?ax=ACxEAsbJbvefAc7bk9_Aon9c00zSwHepjKrP75U3ldHzdywl2vtlZnXgCyoYLgiUXFfGRuNjVaBIlRU3RhgFvwbg6dYgpRnvtKNQ1T6XHnhzFU9zRw7IAhn355oEdEsK-Jh1Ph9TQfj-IxwC1VilHfwlJl8qkuaNz6MV8kmyaer2wgTBQNzd8gWsi2sUkGOKZyE0XDzAK-V79BrrOiPqInV4G8ArT3h9aZ1-ezN4ydttq0upYVmBXaf7TSnJp-RSEl3Lg_zIqEKll5D0rp7QHl7Q_syZ0_HYMlL8XIehTGdX2YKgtAl3ZhJ9eo3Le8z53owktAFKMWed36kSt9NVWwppwbOTJTCk7RX1icx0fkoBsT_TaW7rogKBUknaKoUQOJiMsdaX-JvVwQXDM9H7BhIAviOL0AExLtkQmClxBEy2hGz1MC3-ZQC13WKNoiRMiyhXlwlFsNKBnPWrR_fpGclW30o_O5VV4Z7rp5nZP3T8Begwpxt8O9W7_0VgBc_EQZrpHLTOSvJ8lcMet4dRUrBMu1yrr_JU3xdNAFso40B71zV_UqgqqyoVQu2zlrdFAhuwWibJROAnylslbImdE92EqLNPmclBmZ7BDd0libHlYiBx151RnlBZZ4nQg25UngJhtr9mUWdc6lmKchy06KGD2VEDCZ2LTf6zEvZa-8pTqr8WCQHFP5Cy_WCCv4F7vIQIdH8plJ1a5AOtkw&authuser=0&nonce=qahgndm22aqrq&user=10093904045815650472&hash=3ese143iva5s3h4tis7mh9mom7h0014j", "DKR");
        image.setWidth("40%");

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
