package com.geekbrains.ru.springmvcdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class Product {
    private final long id;
    private final String title;
    private final int cost;

}
