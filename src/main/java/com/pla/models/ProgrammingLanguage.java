package com.pla.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "programming-languages")
@Getter
@Setter
@AllArgsConstructor
public class ProgrammingLanguage {

    @Id
    private String id;
    private String name;
    private String image;
    private Integer votes;
}
