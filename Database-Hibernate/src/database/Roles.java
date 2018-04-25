package database;

public class Roles implements java.io.Serializable
{
    private Integer idRole;
    private String type;

    public Roles()
    {
    }

    public Roles( String type )
    {
        this.type = type;
    }

    public Integer getIdRole()
    {
        return this.idRole;
    }

    public void setIdRole( Integer idRole )
    {
        this.idRole = idRole;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType( String type )
    {
        this.type = type;
    }
}
