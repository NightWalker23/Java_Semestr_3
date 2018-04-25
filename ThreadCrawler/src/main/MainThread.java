package main;

import crawler.CrawlerException;
import logger.ConsoleLogger;
import logger.Logger;
import logger.MailLogger;
import logger.ParallelLogger;
import monitor.Monitor;
import monitor.MonitorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainThread
{
    public static void main( String[] args ) throws IOException, InterruptedException, MonitorException, CrawlerException
    {
        List<String> listOfFiles = new ArrayList<>();
        listOfFiles.add( "students1.txt" );
        listOfFiles.add( "students2.txt" );
        listOfFiles.add( "students3.txt" );
        listOfFiles.add( "students4.txt" );
        listOfFiles.add( "students5.txt" );
        listOfFiles.add( "students6.txt" );
        listOfFiles.add( "students7.txt" );
        listOfFiles.add( "students8.txt" );
        listOfFiles.add( "students9.txt" );
        listOfFiles.add( "students10.txt" );

        ParallelLogger parLog = new ParallelLogger();

        Monitor monit = new Monitor( listOfFiles, 10 );

        monit.studentAddedEvet( ( st ) ->
        {
            parLog.log( "ADDED", st );
            System.out.println( "" );
        } );

        monit.studentRemovedEvet( ( st ) ->
        {
            parLog.log( "REMOVED", st );
            System.out.println( "" );
        } );

        monit.run();
        Thread.sleep( 30000 );
        monit.cancel();

    }
}
