package com.geekbrains.ru.springmvcdemo.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private  long id;
    private  String title;
    private  int cost;
}
