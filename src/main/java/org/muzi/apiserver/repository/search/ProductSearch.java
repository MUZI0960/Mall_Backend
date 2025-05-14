package org.muzi.apiserver.repository.search;

import org.muzi.apiserver.dto.PageRequestDTO;
import org.muzi.apiserver.dto.PageResponseDTO;
import org.muzi.apiserver.dto.ProductDTO;

public interface ProductSearch {

        PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO);
}
