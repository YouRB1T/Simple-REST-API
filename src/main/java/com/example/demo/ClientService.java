package com.example.demo;

import java.util.ArrayList;

public interface ClientService {


    void create(Client client);


    ArrayList<Client> readAll();


    Client read(int id);


    boolean delete(int id);
}
