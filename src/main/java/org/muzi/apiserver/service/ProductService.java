package org.muzi.apiserver.service;

import jakarta.transaction.Transactional;
import org.muzi.apiserver.dto.PageRequestDTO;
import org.muzi.apiserver.dto.PageResponseDTO;
import org.muzi.apiserver.dto.ProductDTO;

@Transactional
public interface ProductService {

    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    Long register(ProductDTO productDTO);


    ProductDTO get(Long pno);

    void modify(ProductDTO productDTO);

    void remove(Long pno);
}
