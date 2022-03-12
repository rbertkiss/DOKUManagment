package com.rkdev.dkr.crm.ui.views.list;

import com.rkdev.dkr.crm.model.Skills;
import com.rkdev.dkr.crm.repository.SkillsRepositroy;
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
public class SkillsEditor extends VerticalLayout implements KeyNotifier {

    private final SkillsRepositroy skillsRepositroy;
    TextField megnevezes = new TextField("Skill megnevezése:");
    ComboBox<String> tudasszint = new ComboBox<>("Tudásszint:");

    Button add = new Button("Mentés", VaadinIcon.PLUS.create());
    Button del = new Button("Törlés",VaadinIcon.TRASH.create());
    Button exit = new Button("Vissza",VaadinIcon.LEVEL_LEFT.create());
    PersonEditor personEditor;

    HorizontalLayout actions = new HorizontalLayout(add, del,exit);
    Dialog scomp = new Dialog();
    Binder<Skills> binder = new Binder<>(Skills.class);

    private Skills skills;
    private ChangeHandler changeHandler;

    @Autowired
    public SkillsEditor(SkillsRepositroy skillsRepositroy) {
        this.skillsRepositroy = skillsRepositroy;
        megnevezes.setWidth("400px");
        tudasszint.setItems("Kezdő","Középhaladó","Haladó", "Mester");
        scomp.setWidth("700px");
        scomp.setHeight("300px");
        scomp.add(new VerticalLayout( new H3("Skillsek hozzáadása / módosítása"),
                new HorizontalLayout(megnevezes,tudasszint), actions));
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
            scomp.close();
        });
    }

    public void save() {
        skillsRepositroy.save(skills);
        changeHandler.onChange();
    }

    public void delete() {
        skillsRepositroy.delete(skills);
    }

    public void editSkill(Skills s) {
        if (s == null) {
            setVisible(false);
            return;
        }

        boolean isPersisted = s.getId() != null;
        if (isPersisted) {
            skills = skillsRepositroy.findById(s.getId()).get();
        } else {
            skills = s;
        }
        binder.setBean(skills);
        setVisible(true);
        megnevezes.focus();
    }



    public void setChangeHandler(ChangeHandler h) { changeHandler = h; }
    public interface ChangeHandler {  void onChange(); }

}
