package monitor;

public class MonitorException extends Exception
{
    public MonitorException()
    {
        System.err.println( "Podana żądana liczba wątków jest błędna!" );
    }
}
