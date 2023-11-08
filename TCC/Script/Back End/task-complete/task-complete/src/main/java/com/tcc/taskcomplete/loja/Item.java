package com.tcc.taskcomplete.loja;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "item")
@Entity(name = "Item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String nome;
    public String preco;
    public String img;

    public Item(dadosItem item) {
        this.nome = item.nome;
        this.preco = item.preco;
        this.img = item.img;
    }
}