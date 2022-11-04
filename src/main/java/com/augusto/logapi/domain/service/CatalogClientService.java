package com.augusto.logapi.domain.service;

import com.augusto.logapi.domain.exception.DomainException;
import com.augusto.logapi.domain.model.Client;
import com.augusto.logapi.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogClientService {

    private ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        boolean existEmail = clientRepository.findByEmail(client.getEmail())
                .stream()
                .anyMatch(clientExist -> !clientExist.equals(client));

        if (existEmail) {
            throw new DomainException("E-mail em uso");
        }

        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public Client find(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new DomainException("Client not found!"));
    }
}
