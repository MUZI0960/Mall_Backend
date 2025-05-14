package org.muzi.apiserver.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.muzi.apiserver.domain.Product;
import org.muzi.apiserver.dto.PageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoyTests {

    @Autowired
    private ProductRepository repository;

    @Test
    public void testInsert(){

        for(int i = 0; i<10 ; i++){

            Product product = Product.builder().pname("TESTTTT").pdesc("TEST desccc").price(100000).build();

            product.addImageString(UUID.randomUUID()+"_"+"IMAGE"+ i+10 +".jpg");
            product.addImageString(UUID.randomUUID()+"_"+"IMAGE"+ i+20 +".jpg");

            repository.save(product);
        }


    }

    @Test
    @Transactional
    public void testRead(){

        Long pno = 1L;
        Optional<Product> result = repository.findById(pno);

        Product product = result.orElseThrow();

        log.info(product);

        log.info(product.getImageList());

    }

    @Test
    public void testRead2(){

        Long pno = 1L;
        Optional<Product> result = repository.selectOne(pno);

        Product product = result.orElseThrow();

        log.info(product);

        log.info(product.getImageList());

    }

    @Commit
    @Transactional
    @Test
    public void testDelete(){

        Long pno = 2L;

        repository.updateToDelete(2L,true);
    }

    @Test
    public void testUpdate(){

        Product product = repository.selectOne(1L).get();

        product.changePrice(3000);
        product.clearList();

        product.addImageString(UUID.randomUUID()+"_"+"PIMAGE1.jpg");
        product.addImageString(UUID.randomUUID()+"_"+"PIMAGE2.jpg");
        product.addImageString(UUID.randomUUID()+"_"+"PIMAGE3.jpg");
        repository.save(product);

    }

    @Test
    public void testList(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());

        Page<Object[]> result = repository.selectList(pageable);

        result.getContent().forEach(arr->log.info(Arrays.toString(arr)));
    }

    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        repository.searchList(pageRequestDTO);

    }

}
