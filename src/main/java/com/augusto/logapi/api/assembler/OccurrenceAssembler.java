package com.augusto.logapi.api.assembler;

import com.augusto.logapi.api.model.OccurrenceModel;
import com.augusto.logapi.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OccurrenceAssembler {

    private ModelMapper modelMapper;

    public OccurrenceModel toModel(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceModel.class);
    }

    public List<OccurrenceModel> toCollection(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toModel).collect(Collectors.toList());
    }
}
