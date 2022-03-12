package com.rkdev.dkr.crm.ui.views.list;

import com.rkdev.dkr.crm.model.School;
import com.rkdev.dkr.crm.repository.SchoolRepository;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class SchoolsEditor extends VerticalLayout implements KeyNotifier {

    private final SchoolRepository schoolRepository;
    TextField iskolaneve = new TextField("Iskola megnevezése:");
    ComboBox<String> kepzetseg = new ComboBox<>("Képzettség:");
    TextField szakirany = new TextField("Szakirány:");
    TextField kezdeseve = new TextField("Kezdés éve:");
    TextField vegzeseve = new TextField("Befejezés éve:");

    Button add = new Button("Mentés", VaadinIcon.PLUS.create());
    Button del = new Button("Törlés",VaadinIcon.TRASH.create());
    Button exit = new Button("Vissza",VaadinIcon.LEVEL_LEFT.create());
    PersonEditor personEditor;

    HorizontalLayout actions = new HorizontalLayout(add, del,exit);
    Dialog sccomp = new Dialog();
    Binder<School> binder = new Binder<>(School.class);

    private School school;
    private ChangeHandler changeHandler;

    @Autowired
    public SchoolsEditor(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
        iskolaneve.setWidth("400px");
        kepzetseg.setItems("Általános Iskola", "Középiskola", "Szakiskola","Felsőoktatási szakképzés","Egyetemi alapképesítés(BA/BSc)","Egyetemi mesterképzés(MA/MSc)","Doktori Iskola (PHD)" );
        sccomp.setWidth("700px");
        sccomp.setHeight("400px");
        sccomp.add(new VerticalLayout( new H3("Tanulmányi adatok hozzáadása / módosítása"),
                new HorizontalLayout(iskolaneve,kepzetseg),
                new HorizontalLayout(szakirany, kezdeseve,vegzeseve),actions));
        binder.bindInstanceFields(this);

        setSpacing(true);

        add.getElement().getThemeList().add("save");
        del.getElement().getThemeList().add("error");

        Dialog savedialog = new Dialog();
        savedialog.add(new Text("Adatsor mentése sikeresen megtörtént"));
        savedialog.setWidth("350px");
        savedialog.setHeight("80px");
        add.addClickListener(e -> {
            save();
            savedialog.open();
        });

        Dialog deletedialog = new Dialog();
        deletedialog.add(new Text("Adatsor törlése sikeresen megtörtént!"));
        deletedialog.setWidth("350px");
        deletedialog.setHeight("80px");
        del.addClickListener(e -> {
            delete();
            deletedialog.open();
        });

        exit.addClickListener(e->{
            sccomp.close();
        });


    }


    public void save() {
        schoolRepository.save(school);
        changeHandler.onChange();
    }

    public void delete() {
        schoolRepository.delete(school);
    }

    public void editSchool(School sc) {
        if (sc == null) {
            setVisible(false);
            return;
        }

        boolean isPersisted = sc.getId() != null;
        if (isPersisted) {
            school = schoolRepository.findById(sc.getId()).get();
        } else {
            school = sc;
        }
        binder.setBean(school);
        setVisible(true);
        iskolaneve.focus();
    }



    public void setChangeHandler(ChangeHandler h) { changeHandler = h; }
    public interface ChangeHandler {  void onChange(); }



}
