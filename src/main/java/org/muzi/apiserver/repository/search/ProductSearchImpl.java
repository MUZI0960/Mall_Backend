package org.muzi.apiserver.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.muzi.apiserver.domain.Product;
import org.muzi.apiserver.domain.QProduct;
import org.muzi.apiserver.domain.QProductImage;
import org.muzi.apiserver.dto.PageRequestDTO;
import org.muzi.apiserver.dto.PageResponseDTO;
import org.muzi.apiserver.dto.ProductDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO) {

        log.info("----------------- searchList -----------------");

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("pno").descending());

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;;

        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.imageList, productImage);

        query.where(productImage.ord.eq(0));

        getQuerydsl().applyPagination(pageable, query);

//        List<Product> productList = query.fetch();
        List<Tuple> productList = query.select(product, productImage).fetch();

        Long count = query.fetchCount();

        log.info("===================================================");
        log.info(productList);

        return null;
    }
}
