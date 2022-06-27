package ReBack.core.controller;

import ReBack.core.data.ClothingSponsor;
import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Product;
import ReBack.core.repository.ClothingSponsorRepository;
import ReBack.core.repository.FinancialSupportRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/api")
public class DonationApiController<financialSupport> {

    @Autowired
    private final ClothingSponsorRepository clothingSponsorRepository;
    @Autowired
    private final FinancialSupportRepository financialSupportRepository;

    @PostMapping("/donation/clothingSponsor") //의류 후원
    public String clothingSponsor(@RequestBody ClothingSponsor clothingSponsor){
        clothingSponsorRepository.save(clothingSponsor);

        return "donation/clothingSponsor";
    }

    @PostMapping("/donation/financialSupport") //금전 후원
    public String FinancialSupport(@RequestBody FinancialSupport financialSupport){
        financialSupportRepository.save(financialSupport);

        return "donation/financialSupport";
    }

    @PutMapping("/donation/update") // 금전 후원 수정
    public void financialUpdate(@RequestBody FinancialSupport financialSupport) {

        System.out.println(financialSupport);
        System.out.println("수정 api");

        financialSupportRepository.save(financialSupport);
    }

    @PutMapping("/donations/update") // 의류 후원 수정
    public void clothingUpdate(@RequestBody ClothingSponsor clothingSponsor) {

        System.out.println(clothingSponsor);
        System.out.println("수정 api");

        clothingSponsorRepository.save(clothingSponsor);
    }

    @DeleteMapping("/donation/delete") // 금전 후원 삭제
    public void financialDelete(@RequestBody FinancialSupport financialSupport) {
        Optional<FinancialSupport> deleteFinancialSupport = financialSupportRepository.findById(financialSupport.getFinancialSupportCode());

        if (deleteFinancialSupport.isPresent()) {
            System.out.println("deleteFinancialSupport ::: " + deleteFinancialSupport);
            financialSupportRepository.delete(financialSupport);
        }
    }

    @DeleteMapping("/donations/delete") // 의류 후원 삭제
    public void clothDelete(@RequestBody ClothingSponsor clothingSponsor) {
        Optional<ClothingSponsor> deleteClothingSponsor = clothingSponsorRepository.findById(clothingSponsor.getClothingSponsorCode());

        if (deleteClothingSponsor.isPresent()) {
            System.out.println("deleteClothingSponsor ::: " + deleteClothingSponsor);
            clothingSponsorRepository.delete(clothingSponsor);
        }
    }

}
