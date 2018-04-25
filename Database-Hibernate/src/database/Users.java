package database;

public class Users implements java.io.Serializable
{
    private Integer idUser;
    private String name;
    private String password;
    private int idRole;

    public Users()
    {
    }

    public Users( String name, String password, int idRole )
    {
        this.name = name;
        this.password = password;
        this.idRole = idRole;
    }

    public Integer getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser( Integer idUser )
    {
        this.idUser = idUser;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public int getIdRole()
    {
        return this.idRole;
    }

    public void setIdRole( int idRole )
    {
        this.idRole = idRole;
    }
}
