package com.example;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer;

@Theme("myTheme")
@SpringUI
//@StyleSheet({"style.css"})
public class MyUI extends UI {
  @Override
  protected void init(VaadinRequest vaadinRequest) {

    // Create a grid
    Grid grid = new Grid();
    grid.setSizeFull();
    grid.setCaption("My Grid");

    // Define some columns
    grid.addColumn("id", Integer.class);
    grid.addColumn("name", String.class);
    grid.addColumn("born", Integer.class);

    // Add some data rows
    grid.addRow(1, "Nicolaus Copernicus", 1543);
    grid.addRow(2, "Galileo Galilei", 1564);
    grid.addRow(3, "Johannes Kepler", 1571);

    grid.getColumn("id").setHeaderCaption("##");

    //WITH THIS ENABLE WE HAVE A BLANK GRID
    grid.getColumn("id").setRenderer(new EditDeleteButtonValueRenderer(new EditDeleteButtonValueRenderer.EditDeleteButtonClickListener() {
    
      @Override
      public void onEdit(final ClickableRenderer.RendererClickEvent event) {
        Notification.show(event.getItemId().toString() + " want's to get edited", Notification.Type.HUMANIZED_MESSAGE);
      }
    
      @Override
      public void onDelete(final com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent event) {
        Notification.show(event.getItemId().toString() + " want's to get delete", Notification.Type.WARNING_MESSAGE);
      }
    }));

    // first of all you need to set a custom style to the column
    grid.setCellStyleGenerator(new Grid.CellStyleGenerator() {
      @Override
      public String getStyle(final Grid.CellReference cellReference) {
        if (cellReference.getPropertyId()
            .equals("id")) {
          return "link-icon";
        } else {
          return null;
        }
      }
    });

    setContent(grid);
  }
}
