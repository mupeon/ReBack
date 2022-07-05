package ReBack.core.service;

import ReBack.core.vo.DonationVO;

import java.util.List;

public interface ChartService {

    public List<DonationVO> countCloths(DonationVO vo) throws Exception;

}
