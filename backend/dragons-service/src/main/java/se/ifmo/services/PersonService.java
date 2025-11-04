package se.ifmo.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.ifmo.gen.model.Person;
import se.ifmo.repositories.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public List<Person> getAll() {
        return personRepository.findAll()
                .stream()
                .map(personEntity -> modelMapper.map(personEntity, Person.class))
                .toList();
    }
}
