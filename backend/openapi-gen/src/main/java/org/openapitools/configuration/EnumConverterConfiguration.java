package org.openapitools.configuration;

import se.ifmo.gen.model.Color;
import se.ifmo.gen.model.DragonCharacter;
import se.ifmo.gen.model.DragonType;
import se.ifmo.gen.model.ImportOperationStatus;
import se.ifmo.gen.model.SortingColumn;
import se.ifmo.gen.model.SortingDirection;
import se.ifmo.gen.model.UserRole;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * This class provides Spring Converter beans for the enum models in the OpenAPI specification.
 *
 * By default, Spring only converts primitive types to enums using Enum::valueOf, which can prevent
 * correct conversion if the OpenAPI specification is using an `enumPropertyNaming` other than
 * `original` or the specification has an integer enum.
 */
@Configuration(value = "org.openapitools.configuration.enumConverterConfiguration")
public class EnumConverterConfiguration {

    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.colorConverter")
    Converter<String, Color> colorConverter() {
        return new Converter<String, Color>() {
            @Override
            public Color convert(String source) {
                return Color.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.dragonCharacterConverter")
    Converter<String, DragonCharacter> dragonCharacterConverter() {
        return new Converter<String, DragonCharacter>() {
            @Override
            public DragonCharacter convert(String source) {
                return DragonCharacter.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.dragonTypeConverter")
    Converter<String, DragonType> dragonTypeConverter() {
        return new Converter<String, DragonType>() {
            @Override
            public DragonType convert(String source) {
                return DragonType.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.importOperationStatusConverter")
    Converter<String, ImportOperationStatus> importOperationStatusConverter() {
        return new Converter<String, ImportOperationStatus>() {
            @Override
            public ImportOperationStatus convert(String source) {
                return ImportOperationStatus.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.sortingColumnConverter")
    Converter<String, SortingColumn> sortingColumnConverter() {
        return new Converter<String, SortingColumn>() {
            @Override
            public SortingColumn convert(String source) {
                return SortingColumn.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.sortingDirectionConverter")
    Converter<String, SortingDirection> sortingDirectionConverter() {
        return new Converter<String, SortingDirection>() {
            @Override
            public SortingDirection convert(String source) {
                return SortingDirection.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.userRoleConverter")
    Converter<String, UserRole> userRoleConverter() {
        return new Converter<String, UserRole>() {
            @Override
            public UserRole convert(String source) {
                return UserRole.fromValue(source);
            }
        };
    }

}
