package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.common.videoApi.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.NoSuchAlgorithmException;

/**
 * 保利威API调用示例接口
 * (设置、开始录播回调暂时不用)
 * @author CJ
 * @date 2019/10/22
 */
@Controller
@Api(tags = {"保利威API调用示例接口"})
public class VideoApiController {

    @Autowired
    private MergeRecordFileCallBackAPI mergeRecordFileCallBackAPI;
    @Autowired
    private FileForwardSaveCallBackAPI fileForwardSaveCallBackAPI;
    @Autowired
    private QueryUserChannelsAPI queryUserChannelsAPI;
    @Autowired
    private MergeRecordFileAPI mergeRecordFileAPI;
    @Autowired
    private FileForwardSaveAPI fileForwardSaveAPI;
    @Autowired
    private QueryVideoClassAPI queryVideoClassAPI;
    @Autowired
    private StartPistonFlowAPI startPistonFlowAPI;
    @Autowired
    private StopPistonFlowAPI stopPistonFlowAPI;
    @Autowired
    private QueryVideoListAPI queryVideoListAPI;
    @Autowired
    private AddLiveChannelAPI addLiveChannelAPI;
    @Autowired
    private QueryVideoOneAPI queryVideoOneAPI;
    @Autowired
    private AddVideoCataAPI addVideoCataAPI;

    /*@Autowired
    private SettingLiveCallBackAPI settingLiveCallBackAPI;
    @Autowired
    private StartLiveCallBackAPI startLiveCallBackAPI;*/

    @ResponseBody
    @ShowLogger(info = "获取所有录制视频分类信息")
    @RequestMapping(path = "/api/queryVideoClass",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有录制视频分类信息",notes = "获取所有录制视频分类信息")
    public QueryVideoClassResponse queryVideoClass(QueryVideoClassRequest request) throws NoSuchAlgorithmException {
        return queryVideoClassAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "获取用户所属的所有频道")
    @RequestMapping(path = "/api/queryUserChannels",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户所属的所有频道",notes = "获取用户所属的所有频道")
    public QueryUserChannelsResponse queryUserChannels(QueryUserChannelsRequest request) throws NoSuchAlgorithmException {
        return queryUserChannelsAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "获取指定频道所有录制视频")
    @RequestMapping(path = "/api/queryVideoList",method = RequestMethod.GET)
    @ApiOperation(value = "获取指定频道所有录制视频",notes = "获取指定频道所有录制视频")
    public QueryVideoListResponse queryRecordVideo(QueryVideoListRequest request) throws NoSuchAlgorithmException {
        return queryVideoListAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "获取指定频道单个录制视频")
    @RequestMapping(path = "/api/queryVideoOne",method = RequestMethod.GET)
    @ApiOperation(value = "获取指定频道单个录制视频",notes = "获取指定频道单个录制视频")
    public QueryVideoOneResponse queryVideoOne(QueryVideoOneRequest request) throws NoSuchAlgorithmException {
        return queryVideoOneAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "合并直播录制文件")
    @RequestMapping(path = "/api/mergeRecordFile",method = RequestMethod.GET)
    @ApiOperation(value = "合并直播录制文件",notes = "合并直播录制文件")
    public MergeRecordFileResponse mergeRecordFile(MergeRecordFileRequest request){
        return mergeRecordFileAPI.process(request);
    }

    //http://94.191.62.87/api/mergeRecordFileCallBack
    @ResponseBody
    @ShowLogger(info = "合并直播录制文件回调函数")
    @RequestMapping(path = "/api/mergeRecordFileCallBack",method = RequestMethod.GET)
    @ApiOperation(value = "合并直播录制文件回调函数",notes = "合并直播录制文件回调函数")
    public MergeRecordFileCallBackResopnse mergeRecordFileCallBack(MergeRecordFileCallBackRequest request){
        return mergeRecordFileCallBackAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "异步批量转存录制文件到点播")
    @RequestMapping(path = "/api/fileForwardSave",method = RequestMethod.POST)
    @ApiOperation(value = "异步批量转存录制文件到点播",notes = "异步批量转存录制文件到点播")
    public FileForwardSaveResponse fileForwardSave(FileForwardSaveRequest request){
        return fileForwardSaveAPI.process(request);
    }

    //http://94.191.62.87/api/fileForwardSaveCallBack
    @ResponseBody
    @ShowLogger(info = "异步批量转存录制文件到点播回调函数")
    @RequestMapping(path = "/api/fileForwardSaveCallBack",method = RequestMethod.GET)
    @ApiOperation(value = "异步批量转存录制文件到点播回调函数",notes = "异步批量转存录制文件到点播回调函数")
    public FileForwardSaveCallBackResopnse fileForwardSaveCallBack(FileForwardSaveCallBackRequest request){
        return fileForwardSaveCallBackAPI.process(request);
    }

    @ResponseBody
    @ApiOperation(value = "保利威根据频道编号开始推流",notes = "保利威根据频道编号开始推流")
    @ShowLogger(info = "保利威根据频道编号开始推流")
    @RequestMapping(path = "/api/startPistonFlow",method = RequestMethod.POST)
    public StartPistonFlowResponse process(StartPistonFlowRequest request){
        return startPistonFlowAPI.process(request);
    }

    @ResponseBody
    @ApiOperation(value = "保利威根据频道编号结束推流",notes = "保利威根据频道编号结束推流")
    @ShowLogger(info = "保利威根据频道编号结束推流")
    @RequestMapping(path = "/api/stopPistonFlow",method = RequestMethod.POST)
    public StopPistonFlowResponse process(StopPistonFlowRequest request){
        return stopPistonFlowAPI.process(request);
    }

    /*@ResponseBody
    @ShowLogger(info = "设置录制回调通知url")
    @RequestMapping(path = "/api/settingLiveCallBack",method = RequestMethod.GET)
    @ApiOperation(value = "设置录制回调通知url",notes = "设置录制回调通知url")
    public SettingLiveCallBackResponse settingLiveCallBack(SettingLiveCallBackRequest request){
        return settingLiveCallBackAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "开始录制回调函数")
    @RequestMapping(path = "/api/startLiveCallBack",method = RequestMethod.GET)
    @ApiOperation(value = "开始录制回调函数",notes = "开始录制回调函数")
    public StartLiveCallBackRequest fileForwardSaveCallBack(StartLiveCallBackRequest request){
        return startLiveCallBackAPI.process(request);
    }*/

    @ResponseBody
    @ShowLogger(info = "新建视频分类")
    @RequestMapping(path = "/api/addVideoCata",method = RequestMethod.GET)
    @ApiOperation(value = "新建视频分类",notes = "新建视频分类")
    public AddVideoCataResopnse fileForwardSaveCallBack(AddVideoCataRequest request) throws NoSuchAlgorithmException {
        return addVideoCataAPI.process(request);
    }

    @ResponseBody
    @ShowLogger(info = "新建直播频道")
    @RequestMapping(path = "/api/addLiveChannel",method = RequestMethod.GET)
    @ApiOperation(value = "新建直播频道",notes = "新建直播频道")
    public AddLiveChannelResopnse fileForwardSaveCallBack(AddLiveChannelRequest request){
        return addLiveChannelAPI.process(request);
    }

}
