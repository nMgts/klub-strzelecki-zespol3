package pl.klubstrzelecki.serwer_klub_strzelecki.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;

@Component
public class CompetitionDTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CompetitionDTO convertCompetitionToCompetitionDTO(Competition competition) {
        CompetitionDTO competitionDTO = modelMapper.map(competition, CompetitionDTO.class);
        return competitionDTO;
    }

    public Competition convertCompetitionDTOtoCompetition(CompetitionDTO competitionDTO) {
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        return competition;
    }
}
