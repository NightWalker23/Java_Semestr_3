package monitor;

import crawler.Crawler;
import crawler.CrawlerException;
import crawler.OrderMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Monitor
{
    private List<String> listOfFiles;
    private int numberOfThreads;
    public CustomThread[] tabThreads = null;
    public boolean looped = false;

    public Monitor( List<String> lof, int n ) throws MonitorException
    {
        listOfFiles = lof;
        if (n < listOfFiles.size() )
            throw ( new MonitorException() );
        else
            numberOfThreads = lof.size();
    }

    List<EventStudent> lisAddSt = new ArrayList<>();
    public void studentAddedEvet( EventStudent st )
    {
        lisAddSt.add( st );
    }

    List<EventStudent> lisDelSt = new ArrayList<>();
    public void studentRemovedEvet( EventStudent st )
    {
        lisDelSt.add( st );
    }

    public synchronized void run() throws InterruptedException, IOException, CrawlerException
    {
        if ( tabThreads == null )
        {
            looped = true;
            tabThreads = new CustomThread[numberOfThreads];
            for ( int i = 0; i < numberOfThreads; i++ )
            {
                tabThreads[i] = new CustomThread( listOfFiles.get( i ), OrderMode.MARK );
                tabThreads[i].setName( "Crawler #" + i );
                tabThreads[i].start();
            }
        }

    }

    public synchronized void cancel() throws InterruptedException
    {
        if ( tabThreads != null )
        {
            looped = false;
            for ( int i = 0; i < numberOfThreads; i++ )
            {
                tabThreads[i].interrupt();
                tabThreads[i].join();
            }
        }
    }

    class CustomThread extends Thread
    {
        private Crawler cr;

        public CustomThread( String source, OrderMode md )
        {
            cr = new Crawler( source, md );
            cr.addLisIteracji( ( it ) ->
                    System.out.println( "\n" + getName() + " Iteracja: " + it ) );

            cr.addLisAddStudent( ( st ) ->
            {
                for ( EventStudent el : lisAddSt )
                    el.handled( st );
            } );

            cr.addLisDelStudent( ( st ) ->
            {
                for ( EventStudent el : lisDelSt )
                    el.handled( st );
            } );
        }

        @Override
        public void run()
        {
            while ( looped )
            {
                try
                {
                    cr.run();
                }
                catch ( CrawlerException e )
                {
                    e.printStackTrace();
                }
                catch ( InterruptedException e )
                {
                    System.out.println( "Przerwano" );
                }
                catch ( IOException e ) {}
            }
        }
    }

}
