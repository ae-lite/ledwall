package io.aelite.ledwall.core;

import io.aelite.ledwall.core.animation.layer.blendmode.BlendMode;
import io.aelite.ledwall.core.util.Color;

/**
 * A Canvas represents a 2-dimensional pixel matrix.
 * @author David Adamson
 */
public class Canvas {

    /**
     * The raw 2D pixel array.
     */
    private int[][] canvas;

    /**
     * Constructs a new Canvas by the given dimensions and color.
     * @param width horizontal dimension
     * @param height vertical dimension
     * @param argb background color in argb hex encoding
     */
    public Canvas(int width, int height, int argb){
        this.canvas = new int[width][height];
        this.fill(argb);
    }

    /**
     * Constructs a new transparent Canvas by the given dimensions.
     * @param width horizontal dimension
     * @param height vertical dimension
     */
    public Canvas(int width, int height){
        this(width, height, 0x00_00_00_00);
    }

    /**
     * Copies the raw pixel data (deepcopy) from another existing Canvas.
     * @param other Canvas to copy from
     */
    public void copyFrom(Canvas other){
        int newWidth = other.getWidth();
        int newHeight = other.getHeight();

        if(this.getWidth() != newWidth || this.getHeight() != newHeight){
            this.canvas = new int[newWidth][newHeight];
        }
        for(int x = 0; x < newWidth; x++){
            for(int y = 0; y < newHeight; y++){
                this.canvas[x][y] = other.canvas[x][y];
            }
        }
    }

    /**
     * Modifies the opacity of the entire Canvas.
     * @param opacity alpha value from 0 to 255
     */
    public void applyOpacity(int opacity){
        int width = this.getWidth();
        int height = this.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int argb = this.canvas[x][y];
                int oldAlpha = Color.getAlpha(argb);
                int newAlpha = (int) ((double) opacity / 255 * oldAlpha);
                this.canvas[x][y] = Color.setAlpha(argb, newAlpha);
            }
        }
    }

    /**
     * Blends another Canvas (layer) on top of this by using a BlendMode.
     * @param top Canvas to apply
     * @param blendMode BlendMode to use
     */
    public void blend(Canvas top, BlendMode blendMode){
        int width = this.getWidth();
        int height = this.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int thisArgb = this.canvas[x][y];
                int topArgb = top.canvas[x][y];
                int blendedArgb = blendMode.blendPixel(thisArgb, topArgb);
                this.canvas[x][y] = blendedArgb;
            }
        }
    }

    /**
     * Get the horizontal width of the Canvas.
     * @return width
     */
    public int getWidth() {
        return this.canvas.length;
    }

    /**
     * Get the vertical height of the Canvas.
     * @return height
     */
    public int getHeight() {
        return this.canvas[0].length;
    }

    /**
     * Set the color of one Pixel by coordinate.
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param argb color encoded in argb hex format
     */
    public void setPixel(int x, int y, int argb) {
        this.canvas[x][y] = argb;
    }

    /**
     * Get the color of one Pixel by coordinate.
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return color encoded in argb hex format
     */
    public int getPixel(int x, int y) {
        return this.canvas[x][y];
    }

    /**
     * Set the color of all Pixels.
     * @param argb color encoded in argb hex format
     */
    public void fill(int argb) {
        for(int x = 0; x < this.canvas.length; x++){
            for(int y = 0; y < this.canvas[0].length; y++){
                this.canvas[x][y] = argb;
            }
        }
    }

    /**
     * Set the color of all Pixels to transparent.
     */
    public void clear() {
        this.fill(0x00_00_00_00);
    }

}
