package uk.co.phoenixfirewingz.share.util;

public class Tuple<T>
{
    private T one, two;

    public Tuple(T one, T two)
    {
        this.one = one;
        this.two = two;
    }

    public T getOne() {
        return one;
    }

    public void setOne(T one) {
        this.one = one;
    }

    public T getTwo() {
        return two;
    }

    public void setTwo(T two) {
        this.two = two;
    }
}
