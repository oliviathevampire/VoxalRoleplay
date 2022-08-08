package uk.co.phoenixfirewingz.client.util;

public class CrashReporter
{
    public void report(Exception e)
    {
        System.out.println(e.getMessage());
        System.gc();
        System.exit(-1);
    }
}
