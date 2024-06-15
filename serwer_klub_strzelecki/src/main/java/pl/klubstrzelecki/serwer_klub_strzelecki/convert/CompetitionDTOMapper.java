package pl.klubstrzelecki.serwer_klub_strzelecki.convert;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompetitionDTOMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CompetitionDTO convertCompetitionToCompetitionDTO(Competition competition) {
        CompetitionDTO competitionDTO = new CompetitionDTO();

        competitionDTO.setId(competition.getId());
        competitionDTO.setName(competition.getName());
        competitionDTO.setDescription(competition.getDescription());
        competitionDTO.setStart_date(competition.getStart_date());
        competitionDTO.setEnd_date(competition.getEnd_date());
        competitionDTO.setShooters_limit(competition.getShooters_limit());

        // Konwersja listy emaili
        List<String> emails = competition.getShooters().stream()
                .map(Shooter::getEmail)
                .collect(Collectors.toList());
        competitionDTO.setEmails(emails);

        return competitionDTO;
    }


    public Competition convertCompetitionDTOtoCompetition(CompetitionDTO competitionDTO) {
        return modelMapper.map(competitionDTO, Competition.class);
    }
}

