package logger;

import student.Student;

public class ConsoleLogger implements Logger
{
    public void log( String status, Student student )
    {
        System.out.print( "\n" + status + " " + student.getMark() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getAge() );
    }
}