package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.common.videoApi.*;
import com.ruoyi.web.controller.common.videoApi.bean.APICata;
import com.ruoyi.web.controller.common.videoApi.bean.APILiveChannel;
import com.ruoyi.web.controller.xiaoyi.bean.OrgPolyWeiBean;
import com.ruoyi.web.controller.xiaoyi.domain.OrgPolyWei;
import com.ruoyi.web.controller.xiaoyi.service.OrgPolyWeiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author CJ
 * @date 2019/11/1
 */
@Controller
@Api(tags = {"添翼申学机构绑定保利威视接口"})
@Slf4j
public class OrgPolyWeiController extends BaseController {

    @Autowired
    private OrgPolyWeiService orgPolyWeiService;
    @Autowired
    private AddLiveChannelAPI addLiveChannelAPI;
    @Autowired
    private AddVideoCataAPI addVideoCataAPI;

    @ShowLogger(info = "校翼-保利威视机构绑定页面")
    @ApiOperation(value = "校翼-保利威视机构绑定页面",notes = "校翼-保利威视机构绑定页面")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/index",method = RequestMethod.GET)
    public String index(){
        return "xiaoyi/polyWei/index";
    }

    @ShowLogger(info = "校翼-机构绑定列表 ")
    @ApiOperation(value = "校翼-机构绑定列表",notes = "校翼-机构绑定列表")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/list",method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo list(OrgPolyWei polyWei){
        startPage();
        List<OrgPolyWei> polyWeis = orgPolyWeiService.queryOrgPolyWeiList(polyWei);
        return getDataTable(polyWeis);
    }

    @ShowLogger(info = "校翼-编辑单个机构绑定数据")
    @ApiOperation(value = "校翼-编辑单个机构绑定数据",notes = "校翼-编辑单个机构绑定数据")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/edit/{id}",method = RequestMethod.GET)
    public String editById(@PathVariable(value = "id") Integer id, Model model){
        OrgPolyWei polyWei = orgPolyWeiService.queryById(id);
        model.addAttribute("polyWei",polyWei);
        return "xiaoyi/polyWei/edit";
    }

    @ResponseBody
    @ShowLogger(info = "校翼-保存单个机构绑定数据")
    @ApiOperation(value = "校翼-保存单个机构绑定数据",notes = "校翼-保存单个机构绑定数据")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/edit",method = RequestMethod.POST)
    public AjaxResult editSave(
            @Validated OrgPolyWei orgPolyWei,
            @RequestParam(name = "status") Integer status
    ) {
        int ubindId = orgPolyWeiService.ubindId(status, orgPolyWei.getId());
        return toAjax(ubindId>0?true:false);
    }

    @ShowLogger(info = "校翼-新增单个机构绑定数据")
    @ApiOperation(value = "校翼-新增单个机构绑定数据",notes = "校翼-新增单个机构绑定数据")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/add",method = RequestMethod.GET)
    public String add(Model model) {
        List<OrgPolyWeiBean> polyWeiBeans = orgPolyWeiService.queryAll();
        model.addAttribute("polyWeis",polyWeiBeans);
        return "xiaoyi/polyWei/add";
    }

    /*@ResponseBody
    @ShowLogger(info = "校翼-保存单个机构绑定数据")
    @ApiOperation(value = "校翼-保存单个机构绑定数据",notes = "校翼-保存单个机构绑定数据")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/add",method = RequestMethod.POST)
    public AjaxResult saveAdd(
            @Validated OrgPolyWei orgPolyWei,
            @RequestParam String orgId,
            @RequestParam String orgName
    ) {
        List<OrgPolyWei> orgPolyWeis = orgPolyWeiService.getOrgPolyWeiByOrgId(orgId);
        if(orgPolyWeis.size()<=0){
            orgPolyWei.setOrgId(orgId);
            orgPolyWei.setOrgName(orgName);
            return AjaxResult.success(orgPolyWeiService.saveOrgPolyWei(orgPolyWei));
        }else{
            return AjaxResult.error("该机构绑定信息已存在！");
        }
    }*/

    @ResponseBody
    @ShowLogger(info = "校翼-保存单个机构绑定数据")
    @ApiOperation(value = "校翼-保存单个机构绑定数据",notes = "校翼-保存单个机构绑定数据")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/add",method = RequestMethod.POST)
    public AjaxResult saveAdd(
            @Validated OrgPolyWei orgPolyWei,
            @RequestParam String orgId,
            @RequestParam String orgName
    ) throws NoSuchAlgorithmException {
        //添加保利威视频分类信息
        if(!StringUtils.isEmpty(orgPolyWei.getCataName())&&!StringUtils.isEmpty(orgPolyWei.getParentId())){
            AddVideoCataRequest cataRequest=new AddVideoCataRequest();
            cataRequest.setCataName(orgPolyWei.getCataName());
            cataRequest.setParentId(orgPolyWei.getParentId());
            AddVideoCataResopnse cataResopnse = addVideoCataAPI.process(cataRequest);
            if(!StringUtils.isEmpty(cataResopnse)&&!StringUtils.isEmpty(cataResopnse.getCata())){
                APICata cata = cataResopnse.getCata();
                String cataid = cata.getCataid();
                orgPolyWei.setCataId(cataid);
            }
        }
        //添加直播频道信息
        if(!StringUtils.isEmpty(orgPolyWei.getCataName())&&!StringUtils.isEmpty(orgPolyWei.getParentId())){
            AddLiveChannelRequest channelRequest=new AddLiveChannelRequest();
            channelRequest.setName(orgPolyWei.getChannelName());
            channelRequest.setPwd(orgPolyWei.getChannelPwd());
            AddLiveChannelResopnse channelResopnse = addLiveChannelAPI.process(channelRequest);
            if(!StringUtils.isEmpty(channelResopnse)&&!StringUtils.isEmpty(channelResopnse.getChannel())){
                APILiveChannel channel = channelResopnse.getChannel();
                orgPolyWei.setChannelId(channel.getChannelId());
            }
        }
        if(!StringUtils.isEmpty(orgId)){
            List<OrgPolyWei> orgPolyWeis = orgPolyWeiService.getOrgPolyWeiByOrgId(orgId);
            if(orgPolyWeis.size()<=0){
                orgPolyWei.setOrgId(orgId);
                orgPolyWei.setOrgName(orgName);
                return AjaxResult.success(orgPolyWeiService.saveOrgPolyWei(orgPolyWei));
            }else{
                return AjaxResult.error("该机构绑定信息已存在！");
            }
        }else{
            return AjaxResult.error("该机构编号为空！");
        }
    }

    @ShowLogger(info = "校翼-查看单个机构")
    @ApiOperation(value = "校翼-查看单个机构",notes = "校翼-查看单个机构")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/bind/{id}",method = RequestMethod.GET)
    public String saveBind(@PathVariable(value = "id")Integer id,Model model) {
        OrgPolyWei polyWei = orgPolyWeiService.queryById(id);
        model.addAttribute("polyWei",polyWei);
        return "xiaoyi/polyWei/bind";
    }

    @ResponseBody
    @ShowLogger(info = "校翼-绑定单个机构")
    @ApiOperation(value = "校翼-绑定单个机构",notes = "校翼-绑定单个机构")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/bind",method = RequestMethod.POST)
    public AjaxResult saveBind(
            @Validated OrgPolyWei orgPolyWei,
            @RequestParam String orgId,
            @RequestParam String orgName
    ) {
        orgPolyWei.setOrgId(orgId);
        orgPolyWei.setOrgName(orgName);
        return AjaxResult.success(orgPolyWeiService.bindId(orgPolyWei));
    }

    @ShowLogger(info = "校翼-删除绑定单个机构")
    @ApiOperation(value = "校翼-删除绑定单个机构", notes = "校翼-删除绑定单个机构")
    @RequestMapping(path = "/xiaoyi/orgPolyWei/remove/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult classRem(@PathVariable(value = "id") Integer id) {
        int row = orgPolyWeiService.deleteOrgPolyWeiById(id);
        return AjaxResult.success(row > 0 ? true : false);
    }

}
