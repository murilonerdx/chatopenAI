package com.murilonerdx.basico;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ChessPiece {
    // Constantes para as peças
    private static final String KING = "king";
    private static final String QUEEN = "queen";
    private static final String ROOK = "rook";
    private static final String BISHOP = "bishop";
    private static final String KNIGHT = "knight";
    private static final String PAWN = "pawn";
    private Map<Object, Object> clientProperties;


    // Propriedades da peça
    private String type;
    private Color color;
    private boolean selected;

    // Construtor
    public ChessPiece(String type, Color color) {
        this.type = type;
        this.color = color;
        this.selected = false;
    }

    // Getters e setters
    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public void putClientProperty(Object key, Object value) {
        if (clientProperties == null) {
            clientProperties = new HashMap<>();
        }
        clientProperties.put(key, value);
    }

    public Object getClientProperty(Object key) {
        if (clientProperties == null) {
            return null;
        }
        return clientProperties.get(key);
    }

}





