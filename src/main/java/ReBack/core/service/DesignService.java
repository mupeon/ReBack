package ReBack.core.service;

import ReBack.core.data.Design;
import ReBack.core.dto.DesignDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.PageResultDTO;
import ReBack.core.security.SecurityUser;

import java.security.Principal;
import java.util.List;

public interface DesignService {
//    Long register(DesignDTO dto);

    PageResultDTO<DesignDTO, Design> getList(PageRequestDTO requestDTO);

    default Design dtoToEntity(DesignDTO dto){
        Design entity = Design.builder()
                .designCode(dto.getDesignCode())
                .designName(dto.getDesignName())
                .designInfo(dto.getDesignInfo())
                .designFileName(dto.getDesignFileName())
                .designFilePath(dto.getDesignFilePath())
                .designPw(dto.getDesignPw())
                .category(dto.getCategoryCode().toCategoryDTO())
//                .category(dto.getCategoryCode().toCategory())
                .material(dto.getMaterialCode().toMaterialDTO())
                .build();
        return entity;
    }

    default DesignDTO entityToDto(Design entity) {
        DesignDTO dto = DesignDTO.builder()
                .designCode(entity.getDesignCode())
                .designName(entity.getDesignName())
                .designInfo(entity.getDesignInfo())
                .designFileName(entity.getDesignFileName())
                .designFilePath(entity.getDesignFilePath())
                .designPw(entity.getDesignPw())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .categoryCode(entity.getCategory().toCategoryDTO())
                .materialCode(entity.getMaterial().toMaterial())
                .build();
        return dto;
    }

    Design save(DesignDTO designDTO, SecurityUser principal);
    void deleteDesign(Long designCode);

    DesignDTO read(Long  designCode);

    void remove(Long designCode);

    void modify(DesignDTO dto);

    public List<Design> getOwnList(PageRequestDTO requestDTO, SecurityUser principal);
}
