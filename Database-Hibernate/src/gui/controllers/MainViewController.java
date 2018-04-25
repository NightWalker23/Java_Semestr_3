package gui.controllers;

import crud.NewHibernateUtil;
import database.Users;
import javafx.fxml.FXML;

public class MainViewController
{
    @FXML UserListViewController usersController;
    @FXML EditViewController editController;
    public Users user;

    public void init( Users el )
    {
        user = el;
        usersController.init();
        editController.init( this, user );
    }

    public void updateList()
    {
        usersController.updateList();
    }
}
