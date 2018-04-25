package Crawler;

import java.io.IOException;

public class MainCrawler
{
    public static void main(String[] args) throws CrawlerException, IOException, InterruptedException
    {
        final Logger[] loggers = new Logger[]
                {
                        new ConsoleLogger(),
                        new MailLogger()
                };

        Crawler cr = new Crawler("students.txt", OrderMode.MARK);

        cr.addLisIteracji((iteracja)->
            System.out.println("\nIteracja: " + iteracja));

        cr.addLisAddStudent((st)->
        {
            for (Logger el: loggers)
                el.log("DODANO", st);
        });

        cr.addLisDelStudent((st)->
        {
            for (Logger el: loggers)
                el.log("USUNIĘTO", st);
        });

        cr.run();
    }
}
