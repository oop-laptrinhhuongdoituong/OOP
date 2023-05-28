package com.example.baitaplonoop.util;

import javafx.scene.control.Button;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


public class FontAwesomeIconButton extends Button {
    public FontAwesomeIconButton(String text) {
        super(text);
    }

    public FontAwesomeIconButton(FontAwesomeIcon icon) {
        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);

        setGraphic(iconView);
    }

    public FontAwesomeIconButton(String text, FontAwesomeIcon icon) {
        super(text);

        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        setGraphic(iconView);
    }
}