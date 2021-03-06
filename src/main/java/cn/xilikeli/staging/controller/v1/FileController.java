package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.bo.FileBO;
import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * <p>
 * 文件上传 API 接口控制器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/file")
@Api(value = "文件上传 API 接口", tags = {"文件上传的相关接口"})
public class FileController {

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 文件上传接口
     *
     * @param multipartHttpServletRequest 携带文件的 request
     * @return 文件信息
     */
    @ScopeLevel
    @PostMapping
    @ApiOperation(value = "文件上传接口", notes = "文件上传接口, 支持多文件上传", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", dataType = "MultipartFile", required = true)
    })
    public List<FileBO> upload(MultipartHttpServletRequest multipartHttpServletRequest) {
        MultiValueMap<String, MultipartFile> fileMap = multipartHttpServletRequest.getMultiFileMap();
        return this.fileService.upload(fileMap);
    }

}