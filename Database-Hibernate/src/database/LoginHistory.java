package database;

import java.util.Date;

public class LoginHistory implements java.io.Serializable
{
    private Integer idLog;
    private int idUser;
    private Date data;

    public LoginHistory()
    {
    }

    public LoginHistory( int idUser, Date data )
    {
        this.idUser = idUser;
        this.data = data;
    }

    public Integer getIdLog()
    {
        return this.idLog;
    }

    public void setIdLog( Integer idLog )
    {
        this.idLog = idLog;
    }

    public int getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser( int idUser )
    {
        this.idUser = idUser;
    }

    public Date getData()
    {
        return this.data;
    }

    public void setData( Date data )
    {
        this.data = data;
    }
}
