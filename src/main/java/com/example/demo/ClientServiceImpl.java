package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ClientServiceImpl implements ClientService {

    private static final AVLTree<Client> CLIENT_REPOSITORY_TREE = new AVLTree<>();
    private static int CLIENT_ID_HOLDER = 0;

    @Override
    public void create(Client client) {
        int clientId = CLIENT_ID_HOLDER;
        CLIENT_ID_HOLDER++;
        CLIENT_REPOSITORY_TREE.insert(clientId, client);
        System.out.println(CLIENT_REPOSITORY_TREE.toList());
    }

    @Override
    public ArrayList<Client> readAll() {
        return new ArrayList<>(CLIENT_REPOSITORY_TREE.toList());
    }

    @Override
    public Client read(int id) {
        System.out.println(CLIENT_REPOSITORY_TREE.get(id));
        return CLIENT_REPOSITORY_TREE.get(id);
    }

    @Override
    public boolean delete(int id) {
        System.out.println(CLIENT_REPOSITORY_TREE.toList());
        return CLIENT_REPOSITORY_TREE.delete(id) != null;
    }
}
