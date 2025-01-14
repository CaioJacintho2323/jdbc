package com.caiojacintho.crud.dominio;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Anime {
    private Integer id;
    private String name;
    private int episodes;
    private Producer producer;

}
