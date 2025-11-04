package se.ifmo.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.ifmo.gen.model.*;
import se.ifmo.models.DragonEntity;
import se.ifmo.models.LocationEntity;
import se.ifmo.models.PersonEntity;

@Configuration
public class ModelMapperConfiguration {

    final Converter<DragonCharacter, JsonNullable<DragonCharacter>> dragonCharacterFromJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected JsonNullable<DragonCharacter> convert(final DragonCharacter source) {
            if (source == null) {
                return null;
            }
            return JsonNullable.of(source);
        }
    };


    final Converter<Integer, JsonNullable<Integer>> integerFromJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected JsonNullable<Integer> convert(final Integer source) {
            if (source == null) {
                return null;
            }
            return JsonNullable.of(source);
        }
    };

    final Converter<JsonNullable<Integer>, Integer> integerToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected Integer convert(final JsonNullable<Integer> source) {
            return source.orElse(null);
        }
    };

    final Converter<Double, JsonNullable<Double>> doubleFromJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected JsonNullable<Double> convert(final Double source) {
            if (source == null) {
                return null;
            }
            return JsonNullable.of(source);
        }
    };

    final Converter<JsonNullable<Double>, Double> doubleToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected Double convert(final JsonNullable<Double> source) {
            return source.orElse(null);
        }
    };

    final Converter<Float, JsonNullable<Float>> floatFromJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected JsonNullable<Float> convert(final Float source) {
            if (source == null) {
                return null;
            }
            return JsonNullable.of(source);
        }
    };

    final Converter<JsonNullable<Float>, Float> floatToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected Float convert(final JsonNullable<Float> source) {
            return source.orElse(null);
        }
    };

    final Converter<Long, JsonNullable<Long>> longFromJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected JsonNullable<Long> convert(final Long source) {
            if (source == null) {
                return null;
            }
            return JsonNullable.of(source);
        }
    };

    final Converter<JsonNullable<Long>, Long> longToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected Long convert(final JsonNullable<Long> source) {
            return source.orElse(null);
        }
    };


    final Converter<JsonNullable<String>, String> stringToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected String convert(final JsonNullable<String> source) {
            return source.orElse(null);
        }
    };

    final Converter<String, JsonNullable<String>> stringFromJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected JsonNullable<String> convert(final String source) {
            if (source == null) {
                return null;
            }
            return JsonNullable.of(source);
        }
    };

    final Converter<JsonNullable<Person>, Person> personToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected Person convert(final JsonNullable<Person> source) {
            return source.orElse(null);
        }
    };

    final Converter<JsonNullable<DragonCharacter>, DragonCharacter> dragonCharacterToJsonNullableConverter = new AbstractConverter<>() {
        @Override
        protected DragonCharacter convert(final JsonNullable<DragonCharacter> source) {
            return source.orElse(null);
        }
    };

    final Converter<se.ifmo.models.Color, JsonNullable<se.ifmo.gen.model.Color>> hairColorToJsonNullable =
            new AbstractConverter<>() {
                @Override
                protected JsonNullable<se.ifmo.gen.model.Color> convert(se.ifmo.models.Color source) {
                    if (source == null) {
                        return JsonNullable.of(null);
                    }
                    se.ifmo.gen.model.Color dtoColor = se.ifmo.gen.model.Color.fromValue(source.name());
                    return JsonNullable.of(dtoColor);
                }
            };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(dragonCharacterFromJsonNullableConverter);
        mapper.addConverter(integerFromJsonNullableConverter);
        mapper.addConverter(integerToJsonNullableConverter);
        mapper.addConverter(doubleFromJsonNullableConverter);
        mapper.addConverter(doubleToJsonNullableConverter);
        mapper.addConverter(longFromJsonNullableConverter);
        mapper.addConverter(longToJsonNullableConverter);
        mapper.addConverter(floatFromJsonNullableConverter);
        mapper.addConverter(floatToJsonNullableConverter);
        mapper.addConverter(stringToJsonNullableConverter);
        mapper.addConverter(stringFromJsonNullableConverter);
        mapper.addConverter(personToJsonNullableConverter);
        mapper.addConverter(dragonCharacterToJsonNullableConverter);
        mapper.addConverter(hairColorToJsonNullable);

        mapper.createTypeMap(DragonEntity.class, Dragon.class)
                .addMappings(mapping -> {
                    mapping.using(new AbstractConverter<PersonEntity, JsonNullable<Person>>() {
                        @Override
                        protected JsonNullable<Person> convert(PersonEntity source) {
                            if (source == null) {
                                return JsonNullable.of(null);
                            }
                            Person person = mapper.map(source, Person.class);
                            return JsonNullable.of(person);
                        }
                    }).map(DragonEntity::getKiller, Dragon::setKiller);

                    mapping.using(new AbstractConverter<se.ifmo.models.DragonCharacter, JsonNullable<DragonCharacter>>() {
                        @Override
                        protected JsonNullable<DragonCharacter> convert(se.ifmo.models.DragonCharacter source) {
                            if (source == null) {
                                return JsonNullable.of(null);
                            }
                            DragonCharacter dtoCharacter = DragonCharacter.fromValue(source.name());
                            return JsonNullable.of(dtoCharacter);
                        }
                    }).map(DragonEntity::getCharacter, Dragon::setCharacter);
                });

        mapper.createTypeMap(DragonCreate.class, DragonEntity.class)
                .addMappings(mapping -> {
                    mapping.skip(DragonEntity::setId);

                    mapping.skip(DragonEntity::setCoordinatesEntity);
                    mapping.skip(DragonEntity::setCave);
                    mapping.skip(DragonEntity::setHead);
                    mapping.skip(DragonEntity::setKiller);

                    mapping.using(new AbstractConverter<JsonNullable<DragonCharacter>, se.ifmo.models.DragonCharacter>() {
                        @Override
                        protected se.ifmo.models.DragonCharacter convert(JsonNullable<DragonCharacter> source) {
                            DragonCharacter sourceEnum = source.orElse(null);
                            if (sourceEnum == null) {
                                return null;
                            }
                            return se.ifmo.models.DragonCharacter.valueOf(sourceEnum.name());
                        }
                    }).map(DragonCreate::getCharacter, DragonEntity::setCharacter);
                });

        mapper.createTypeMap(se.ifmo.gen.model.DragonCreateKiller.class, se.ifmo.models.PersonEntity.class)
                .addMappings(mapping -> {
                    mapping.skip(se.ifmo.models.PersonEntity::setId);

                    mapping.using(new AbstractConverter<JsonNullable<se.ifmo.gen.model.Color>, se.ifmo.models.Color>() {
                        @Override
                        protected se.ifmo.models.Color convert(JsonNullable<se.ifmo.gen.model.Color> source) {
                            se.ifmo.gen.model.Color dtoColor = source.orElse(null);
                            if (dtoColor == null) {
                                return null;
                            }
                            return se.ifmo.models.Color.valueOf(dtoColor.name());
                        }
                    }).map(se.ifmo.gen.model.DragonCreateKiller::getHairColor, se.ifmo.models.PersonEntity::setHairColor);

                    mapping.using(new AbstractConverter<JsonNullable<se.ifmo.gen.model.Location>, se.ifmo.models.LocationEntity>() {
                        @Override
                        protected se.ifmo.models.LocationEntity convert(JsonNullable<se.ifmo.gen.model.Location> source) {
                            se.ifmo.gen.model.Location dtoLocation = source.orElse(null);
                            if (dtoLocation == null) {
                                return null;
                            }
                            return mapper.map(dtoLocation, se.ifmo.models.LocationEntity.class);
                        }
                    }).map(se.ifmo.gen.model.DragonCreateKiller::getLocation, se.ifmo.models.PersonEntity::setLocationEntity);
                });

        mapper.createTypeMap(se.ifmo.gen.model.Location.class, se.ifmo.models.LocationEntity.class)
                .addMappings(m -> m.skip(se.ifmo.models.LocationEntity::setId));

        Converter<se.ifmo.models.LocationEntity, JsonNullable<se.ifmo.gen.model.Location>> locationToJsonNullable =
                new AbstractConverter<>() {
                    @Override
                    protected JsonNullable<se.ifmo.gen.model.Location> convert(se.ifmo.models.LocationEntity source) {
                        if (source == null) {
                            return JsonNullable.of(null);
                        }
                        se.ifmo.gen.model.Location dtoLocation = mapper.map(source, se.ifmo.gen.model.Location.class);
                        return JsonNullable.of(dtoLocation);
                    }
                };
        mapper.addConverter(locationToJsonNullable);
        mapper.createTypeMap(LocationEntity.class, Location.class)
                .addMappings(m -> m.map(LocationEntity::getName, Location::setName));

        return mapper;
    }
}
