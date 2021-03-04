package com.example.demo.gui;

import com.example.demo.dto.CarDto;
import com.example.demo.url.Url;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

        //MyCustomLayout upperLayout = new MyCustomLayout();

        //upperLayout.addItemWithLabel("", getVehicleButton);

        add(carGrid, getVehicleButton);
    }

    public void addVehiclesToGrid(){
        carGrid.setItems(url.getCars());
    }
}
