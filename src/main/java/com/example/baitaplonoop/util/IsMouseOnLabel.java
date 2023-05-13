package com.example.baitaplonoop.util;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class IsMouseOnLabel {
    public static boolean isMouseOnLabel(Label label, MouseEvent event) {
        Bounds bounds = label.getBoundsInParent();
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        return bounds.contains(mouseX, mouseY);
    }
}
