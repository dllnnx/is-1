package se.ifmo.configurations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import se.ifmo.gen.model.DragonCreateCave;
import se.ifmo.gen.model.DragonCreateCoordinates;
import se.ifmo.gen.model.DragonCreateHead;
import se.ifmo.gen.model.DragonCreateKiller;

import java.io.IOException;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.registerModule(new JsonNullableModule());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(DragonCreateCave.class, new DragonCreateCaveDeserializer());
        module.addDeserializer(DragonCreateHead.class, new DragonCreateHeadDeserializer());
        module.addDeserializer(DragonCreateCoordinates.class, new DragonCreateCoordinatesDeserializer());
        module.addDeserializer(DragonCreateKiller.class, new DragonCreateKillerDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }

    static class DragonCreateCaveDeserializer extends JsonDeserializer<DragonCreateCave> {
        @Override
        public DragonCreateCave deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);

            if (node.isNumber()) {
                DragonCreateCave cave = new DragonCreateCave();
                cave.setId(node.asInt());
                return cave;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JsonNullableModule());
                return mapper.treeToValue(node, DragonCreateCave.class);
            }
        }
    }

    static class DragonCreateHeadDeserializer extends JsonDeserializer<DragonCreateHead> {
        @Override
        public DragonCreateHead deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);

            if (node.isNumber()) {
                DragonCreateHead head = new DragonCreateHead();
                head.setId(node.asInt());
                return head;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JsonNullableModule());
                return mapper.treeToValue(node, DragonCreateHead.class);
            }
        }
    }

    static class DragonCreateCoordinatesDeserializer extends JsonDeserializer<DragonCreateCoordinates> {
        @Override
        public DragonCreateCoordinates deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);

            if (node.isNumber()) {
                DragonCreateCoordinates coords = new DragonCreateCoordinates();
                coords.setId(node.asInt());
                return coords;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JsonNullableModule());
                return mapper.treeToValue(node, DragonCreateCoordinates.class);
            }
        }
    }

    static class DragonCreateKillerDeserializer extends JsonDeserializer<DragonCreateKiller> {
        @Override
        public DragonCreateKiller deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);

            if (node.isNumber()) {
                DragonCreateKiller killer = new DragonCreateKiller();
                killer.setId(node.asInt());
                return killer;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JsonNullableModule());
                return mapper.treeToValue(node, DragonCreateKiller.class);
            }
        }
    }
}
