package org.muzi.apiserver.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.muzi.apiserver.dto.PageRequestDTO;
import org.muzi.apiserver.dto.PageResponseDTO;
import org.muzi.apiserver.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> responseDTO = productService.getList(pageRequestDTO);

        log.info(responseDTO.getDtoList());
    }

    @Test
    public void testRegister(){

        ProductDTO productDTO = ProductDTO.builder()
                .pname("새로운 상품")
                .pdesc("새로운 상품 설명")
                .price(39990)
                .build();

        // uuid가 있어야함.
        productDTO.setUploadedFileNames(
                List.of(
                        UUID.randomUUID()+"_"+"test1.jpg",
                        UUID.randomUUID()+"_"+"test2.jpg"
                )
        );

        productService.register(productDTO);

    }


}
