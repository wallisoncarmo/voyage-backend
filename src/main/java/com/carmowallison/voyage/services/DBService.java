package com.carmowallison.voyage.services;

import java.text.ParseException;

import com.carmowallison.voyage.domain.Category;
import com.carmowallison.voyage.domain.Photograph;
import com.carmowallison.voyage.repositoties.CategoryRepository;
import com.carmowallison.voyage.repositoties.PhotographRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PhotographRepository photographRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BCryptPasswordEncoder bc;

    public void instantieateTestDatabase() throws ParseException {

        System.out.println("Adiciona categorias");
        Category praia = new Category(null,"Praia",true);
        Category cidade = new Category(null,"Cidade",true);
        Category casa = new Category(null,"Casa",true);

        categoryRepository.saveAll(Arrays.asList(praia,cidade,casa));

        System.out.println("Adiciona fotos");
        Photograph photograph1 = new Photograph(null,"Aeroporto","Começando a viagem",true,cidade,-15.7997654,-47.8644715);
        Photograph photograph2 = new Photograph(null,"Na Praia","Curtindo a praia",true,praia,-12.8253868,-38.2227197);
        Photograph photograph3 = new Photograph(null,"Por do Sol ","Por do Sol",true,praia,-12.8259139,-38.2227915);
        Photograph photograph4 = new Photograph(null,"Brindando","Brindando ao por do sol",true,praia,-12.8262452,-38.2232747);
        Photograph photograph5 = new Photograph(null,"Praia","Beira da praia",true,praia,-12.8248192,-38.2223529);
        Photograph photograph6 = new Photograph(null,"Hotel","Vista da hotel",true,cidade,-12.8244273,-38.2235012);
        Photograph photograph7 = new Photograph(null,"Ponte","Vista da ponte",true,cidade,-12.8222711,-38.220275);
        Photograph photograph8 = new Photograph(null,"No topo","Vista do topo da montanha",true,cidade,-12.8044391,-38.2142283);
        Photograph photograph9 = new Photograph(null,"Estrada","Correndo na estrada",true,cidade,-12.7950387,-38.2102662);
        Photograph photograph10 = new Photograph(null,"Rio","Andando de caiaque no rio",true,cidade,-12.7761265,-38.2046043);
        Photograph photograph11 = new Photograph(null,"Avião","Esperando mo aviao chegar",true,cidade,-12.7620622,-38.1851034);
        Photograph photograph12 = new Photograph(null,"Por do Sol","Vista do por do sol",true,praia,-12.7644716,-38.1732464);
        photographRepository.saveAll(Arrays.asList(photograph1,photograph2,photograph3,photograph4,photograph5,photograph6,photograph7,photograph8,photograph9,photograph10,photograph11,photograph12));

    }
}
