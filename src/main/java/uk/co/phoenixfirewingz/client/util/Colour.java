package uk.co.phoenixfirewingz.client.util;

public class Colour
{
    private float r,g,b,a;

    public Colour(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Colour(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1.0f;
    }

    public Colour(float colour) {
        this.r = colour;
        this.g = colour;
        this.b = colour;
        this.a = 1.0f;
    }

    public Colour(float colour,float alpha) {
        this.r = colour;
        this.g = colour;
        this.b = colour;
        this.a = alpha;
    }
    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }
}
