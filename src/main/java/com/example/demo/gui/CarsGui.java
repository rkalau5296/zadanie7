package com.example.demo.gui;

import com.example.demo.dto.CarDto;
import com.example.demo.url.Url;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("vehicles")
public class CarsGui extends VerticalLayout {

    private final Url url;
    private final Grid<CarDto> carGrid;


    @Autowired
    public CarsGui(Url url){
        this.url = url;
        carGrid = new Grid<>(CarDto.class);
        carGrid.setMaxWidth("800px");
        carGrid.setWidth("100%");


        Button getVehicleButton = new Button("Pobierz pojazdy");
        getVehicleButton.addClickListener(buttonClickEvent -> addVehiclesToGrid());

        Button newVehicle = new Button("New vehicle");
        newVehicle.addClickListener(buttonClickEvent -> addNewVehicleDialog().open());

        add(carGrid, getVehicleButton, newVehicle);
    }

    public void addVehiclesToGrid(){
        carGrid.setItems(url.getCars());
    }

    public Dialog addNewVehicleDialog() {

        Dialog dialog = new Dialog(new Text("Add new vehicle"));

        IntegerField integerField = new IntegerField("Vehicle Id");
        TextField brand = new TextField("Brand");
        TextField color = new TextField("Color");
        TextField model = new TextField("Model");
        TextField production_date = new TextField("Production Date");

        Button save = new Button("Save", buttonClickEvent -> {
            if(integerField.getValue()==null|| brand.getValue()==null || color.getValue()==null||model.getValue()==null||production_date.getValue()==null)
            {
                Notification notification = Notification.show(
                        "Nie wprowadzono wszystkich danych. Wypełnij wszystkie pola.");
                add(notification);
            }
            url.postCar(new CarDto(integerField.getValue(), brand.getValue(), color.getValue(), model.getValue(),production_date.getValue()));
            addVehiclesToGrid();
            dialog.close();
        });
        Button cancel = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout upperLayout = new MyCustomLayout();

        upperLayout.addItemWithLabel("",integerField);
        upperLayout.addItemWithLabel("",brand);
        upperLayout.addItemWithLabel("",color);
        upperLayout.addItemWithLabel("",model);
        upperLayout.addItemWithLabel("",production_date);
        upperLayout.addItemWithLabel("", cancel);
        upperLayout.addItemWithLabel("",save);

        dialog.add(upperLayout );

        return dialog;
    }
}
