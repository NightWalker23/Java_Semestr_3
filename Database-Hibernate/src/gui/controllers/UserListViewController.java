package gui.controllers;

import crud.UsersCRUD;
import database.Users;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class UserListViewController
{
    private ObservableList<String> users = FXCollections.observableArrayList();
    @FXML private ListView<String> userList = new ListView<>(users);
    private List<Users> us = new ArrayList<>();
    
    public void init()
    {
        updateList();
        userList.setItems( users );
    }    
    
    public void updateList()
    {
        UsersCRUD uc = new UsersCRUD();
        
        users.clear();
        us = uc.getAllUsers();
        String role;
        
        for( Users el: us )
        {
            if( el.getIdRole() == 1 ) role = "ADMIN";
                else role = "USER";
            
            users.add( el.getIdUser() + "\t" + el.getName() + "\t" + role );
        }
    }
    
}
