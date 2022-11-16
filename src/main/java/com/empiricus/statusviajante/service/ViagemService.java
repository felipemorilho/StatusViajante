package com.empiricus.statusviajante.service;

import com.empiricus.statusviajante.model.ViagemModel;
import com.empiricus.statusviajante.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    public ViagemModel salvarViagem(ViagemModel viagemModel) throws Exception {

        Date dataIda = viagemModel.getDataIda();
        Date dataVolta = viagemModel.getDataVolta();

        if(dataIda.compareTo(dataVolta) > 0) {
            throw new Exception("Data de ida não pode ser maior que a data de volta.");
        } else {
            return viagemRepository.save(viagemModel);
        }
    }

    public List<ViagemModel> buscarTodasViagens() {
        return viagemRepository.findAll();
    }

    public Optional<ViagemModel> buscarViagemId(Long id) {
        return viagemRepository.findById(id);
    }

    public void deletarViagemId(Long id) {
        viagemRepository.deleteById(id);
    }

    public List<ViagemModel> buscarViagemPorUsuario(Long idUsuario) {
        return viagemRepository.findByUsuario_idUsuario(idUsuario);
    }
}
