package io.aelite.ledwall.core.animation.control;

public class Slider extends Control {

    private double min;
    private double max;
    private double step;
    private double value;

    public Slider(double min, double max, double step, double value) {
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
