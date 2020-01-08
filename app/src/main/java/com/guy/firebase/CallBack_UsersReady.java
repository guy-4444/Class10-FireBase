package com.guy.firebase;

import java.util.ArrayList;

public interface CallBack_UsersReady {
    void usersReady(ArrayList<User> users);
    void error();
}
