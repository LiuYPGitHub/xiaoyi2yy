package com.ruoyi.web.controller.blog.service.impl;

import com.ruoyi.web.controller.blog.dao.LeadsMapper;
import com.ruoyi.web.controller.blog.domain.Leads;
import com.ruoyi.web.controller.blog.service.LeadsService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案例信息
 */
@Log4j2
@Service
public class LeadsServiceImpl implements LeadsService {
    @Autowired
    private LeadsMapper leadsMapper;

    @Autowired
    private Mapper mapper;

    /**
     * 信息集合
     */
    @Override
    public List<Leads> selectLeadsList(Leads leads) {
        return leadsMapper.selectLeadsList(leads);
    }

    @Override
    public Leads selectDetailedById(String leId) {
        Leads le = leadsMapper.selectDetailedById(leId);
        String json = le.getLeIntenType();
        String t = json.replaceAll("\\\"", "");
        String replaceAll = t.replace("[", "").replace("]", "");
        le.setLeIntenType(replaceAll);

        if (StringUtils.isNotEmpty(le.getCity())) {
            Leads code = leadsMapper.selectCode(le.getCity());
            String qu = code.getAreaName();  // 区
//        System.out.println("///////////" + qu);
            Leads shir = leadsMapper.selectCode(code.getAreaParentCode());
            String zhixiashi = shir.getAreaName();  // 直辖市
//        System.out.println("///////////" + zhixiashi);
            Leads shi1 = leadsMapper.selectCode(shir.getAreaParentCode());
            String shi = shi1.getAreaName(); // 市
//        System.out.println("///////////" + shi);
            Leads sheng1 = leadsMapper.selectCode(shi1.getAreaParentCode());
            String sheng = sheng1.getAreaName(); // 省
//        System.out.println("///////////" + sheng);
            if (shi.equals("重庆市")) {
                le.setCity(shi + "-" + zhixiashi + "-" + qu);
                return le;
            } else if (zhixiashi.equals("市辖区")) {
                le.setCity(sheng + "-" + shi + "-" + qu);
                return le;
            } else {
                le.setCity(shi + "-" + zhixiashi + "-" + qu);
                return le;
            }
        } else {
            return le;
        }
    }

//    @Override
//    public Leads selectLeads(String leId) {
//        return leadsMapper.selectLeads(leId);
//    }
}
