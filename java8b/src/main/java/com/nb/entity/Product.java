package com.nb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yunfu.ye
 * @date 2022/9/8 15:11
 * @desciption:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    int id;
    String name;
    Double price;
}
