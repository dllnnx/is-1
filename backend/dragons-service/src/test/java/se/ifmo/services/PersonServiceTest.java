package se.ifmo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import se.ifmo.gen.model.Person;
import se.ifmo.models.PersonEntity;
import se.ifmo.repositories.PersonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PersonService Unit Tests")
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository, modelMapper);
    }

    @Test
    @DisplayName("Should return list of Person when getAll is called")
    void getAll_ShouldReturnListOfPerson() {
        PersonEntity entity1 = PersonEntity.builder()
                .id(1L)
                .name("John Doe")
                .weight(75.5)
                .build();
        PersonEntity entity2 = PersonEntity.builder()
                .id(2L)
                .name("Jane Smith")
                .weight(65.0)
                .build();

        Person dto1 = new Person();
        dto1.setId(1);
        dto1.setName("John Doe");
        dto1.setWeight(75.5);

        Person dto2 = new Person();
        dto2.setId(2);
        dto2.setName("Jane Smith");
        dto2.setWeight(65.0);

        when(personRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(modelMapper.map(entity1, Person.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, Person.class)).thenReturn(dto2);

        List<Person> result = personService.getAll();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(personRepository, times(1)).findAll();
    }
}

